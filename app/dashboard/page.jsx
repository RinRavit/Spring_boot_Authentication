"use client";

import { useEffect, useState } from "react";
import API from "../utils/api";
import React from "react";
import { Pencil, Plus, Trash2, UserPlus } from "lucide-react";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Alert, AlertDescription } from "@/components/ui/alert";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { useRouter } from "next/navigation";

const CourseManagement = () => {
  const router = useRouter();
  const [currentUser, setCurrentUser] = useState({
    id: 1,
    role: "SUPER_ADMIN",
    assignedCourseId: null,
  });

  const [courses, setCourses] = useState([]);
  const [error, setError] = useState("");
  const [users, setUsers] = useState([]);
  const [newCourseTitle, setNewCourseTitle] = useState("");
  const [courseToDelete, setCourseToDelete] = useState(null);
  const [showDeleteDialog, setShowDeleteDialog] = useState(false);
  const [showAddDialog, setShowAddDialog] = useState(false);

  // Fetch Users (Admin Access Required)
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await API.get("/admin/users"); // Admin API call
        setUsers(response.data);
      } catch (err) {
        setError("You are not authorized to view this page.");
      }
    };
    fetchData();
  }, []);

  // Fetch Courses (Simulating backend response)
  useEffect(() => {
    setCourses([
      {
        id: 1,
        title: "Course 1",
        adminId: 2,
        assignedUsers: [1, 2, 3],
        description: "Course description here",
      },
    ]);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    router.push("/login");
  };

  const handleAddCourse = () => {
    if (!newCourseTitle.trim()) {
      alert("Please enter a course name");
      return;
    }
    const newCourse = {
      id: courses.length + 1,
      title: newCourseTitle.trim(),
      adminId: null,
      assignedUsers: [],
      description: "New course description",
    };
    setCourses([...courses, newCourse]);
    setNewCourseTitle("");
    setShowAddDialog(false);
  };

  const handleDeleteConfirm = () => {
    if (courseToDelete) {
      setCourses(courses.filter((course) => course.id !== courseToDelete.id));
      setShowDeleteDialog(false);
      setCourseToDelete(null);
    }
  };

  const initiateDelete = (course) => {
    setCourseToDelete(course);
    setShowDeleteDialog(true);
  };

  const getVisibleCourses = () => {
    switch (currentUser.role) {
      case "SUPER_ADMIN":
        return courses;
      case "ADMIN":
        return courses.filter((course) => course.adminId === currentUser.id);
      case "USER":
        return courses.filter((course) =>
          course.assignedUsers.includes(currentUser.id)
        );
      default:
        return [];
    }
  };

  return (
    <div className="p-6 min-h-screen bg-gray-200">
      <div className="flex justify-between items-center mb-6">
        <div>
          <h1 className="text-xl font-semibold">Course Management System</h1>
          <p className="text-sm text-gray-600">Logged in as: {currentUser.role}</p>
        </div>

        <div className="flex gap-4">
          {currentUser.role === "SUPER_ADMIN" && (
            <Dialog open={showAddDialog} onOpenChange={setShowAddDialog}>
              <DialogTrigger asChild>
                <Button className="bg-violet-500 hover:bg-violet-600 text-white">
                  <Plus className="w-4 h-4 mr-2" />
                  Add course
                </Button>
              </DialogTrigger>
              <DialogContent>
                <DialogHeader>
                  <DialogTitle>Add New Course</DialogTitle>
                </DialogHeader>
                <div className="grid gap-4 py-4">
                  <div className="grid gap-2">
                    <Label htmlFor="name">Course Name</Label>
                    <Input
                      id="name"
                      value={newCourseTitle}
                      onChange={(e) => setNewCourseTitle(e.target.value)}
                      placeholder="Enter course name"
                    />
                  </div>
                </div>
                <DialogFooter>
                  <Button variant="outline" onClick={() => setShowAddDialog(false)}>
                    Cancel
                  </Button>
                  <Button onClick={handleAddCourse}>Create Course</Button>
                </DialogFooter>
              </DialogContent>
            </Dialog>
          )}

          <Button onClick={handleLogout} className="bg-red-500 hover:bg-red-600 text-white">
            Logout
          </Button>
        </div>
      </div>

      {/* Display Admin Users */}
      {error ? (
        <Alert className="mb-4">
          <AlertDescription>{error}</AlertDescription>
        </Alert>
      ) : (
        <div className="mb-6">
          <h2 className="text-lg font-medium">Admin Users</h2>
          <ul className="list-disc ml-5 text-gray-700">
            {users.map((user) => (
              <li key={user.id}>{user.username}</li>
            ))}
          </ul>
        </div>
      )}

      {/* Courses Section */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {getVisibleCourses().map((course) => (
          <Card key={course.id} className="relative bg-white">
            <CardContent className="p-6">
              <div className="absolute top-2 right-2 flex gap-2">
                {(currentUser.role === "SUPER_ADMIN" ||
                  (currentUser.role === "ADMIN" && course.adminId === currentUser.id)) && (
                  <button className="p-2 text-gray-600 hover:text-gray-900">
                    <Pencil className="w-4 h-4" />
                  </button>
                )}
                {currentUser.role === "SUPER_ADMIN" && (
                  <>
                    <button className="p-2 text-gray-600 hover:text-gray-900">
                      <UserPlus className="w-4 h-4" />
                    </button>
                    <button
                      onClick={() => initiateDelete(course)}
                      className="p-2 text-red-600 hover:text-red-900"
                    >
                      <Trash2 className="w-4 h-4" />
                    </button>
                  </>
                )}
              </div>
              <h2 className="text-lg font-medium">{course.title}</h2>
              <p className="text-sm text-gray-600 mt-2">{course.description}</p>
            </CardContent>
          </Card>
        ))}
      </div>

      {/* Delete Confirmation Dialog */}
      <Dialog open={showDeleteDialog} onOpenChange={setShowDeleteDialog}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Confirm Deletion</DialogTitle>
          </DialogHeader>
          <div className="py-4">
            <p>Are you sure you want to delete "{courseToDelete?.title}"?</p>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setShowDeleteDialog(false)}>
              Cancel
            </Button>
            <Button variant="destructive" onClick={handleDeleteConfirm}>
              Delete
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default CourseManagement;
