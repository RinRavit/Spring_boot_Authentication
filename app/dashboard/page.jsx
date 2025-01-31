"use client";

import { useEffect, useState } from "react";
import API from "../utils/api";
import { Pencil, Plus, Trash2, UserPlus } from "lucide-react";
import { useRouter } from "next/navigation";

const CourseManagement = () => {
  const router = useRouter();
  const [currentUser, setCurrentUser] = useState(null); // Logged-in User
  const [courses, setCourses] = useState([]);
  const [error, setError] = useState("");
  const [users, setUsers] = useState([]);
  const [newCourseTitle, setNewCourseTitle] = useState("");

  useEffect(() => {
    const fetchUserAndData = async () => {
      try {
        const userResponse = await API.get("/auth/me"); // Fetch Logged-in User
        setCurrentUser(userResponse.data);

        const coursesResponse = await API.get("/courses"); // Fetch Courses
        setCourses(coursesResponse.data);

        const usersResponse = await API.get("/users"); // Fetch Users
        setUsers(usersResponse.data);
      } catch (err) {
        setError("Failed to load data.");
      }
    };
    fetchUserAndData();
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    router.push("/login");
  };

  const handleAddCourse = async () => {
    if (!newCourseTitle.trim()) {
      alert("Please enter a course name");
      return;
    }
    const newCourse = {
      title: newCourseTitle.trim(),
      adminId: null, // No Admin assigned yet
      assignedUsers: [],
    };

    try {
      const response = await API.post("/courses", newCourse);
      setCourses([...courses, response.data]);
      setNewCourseTitle("");
    } catch (err) {
      alert("Error adding course");
    }
  };

  const handleDeleteCourse = async (id) => {
    try {
      await API.delete(`/courses/${id}`);
      setCourses(courses.filter((course) => course.id !== id));
    } catch (err) {
      alert("Error deleting course");
    }
  };

  const assignAdminToCourse = async (courseId, adminId) => {
    try {
      await API.put(`/courses/${courseId}/assign-admin`, { adminId });
      setCourses(
        courses.map((course) =>
          course.id === courseId ? { ...course, adminId } : course
        )
      );
    } catch (err) {
      alert("Error assigning admin");
    }
  };

  const assignUserToCourse = async (courseId, userId) => {
    try {
      await API.put(`/courses/${courseId}/assign-user`, { userId });
      setCourses(
        courses.map((course) =>
          course.id === courseId
            ? { ...course, assignedUsers: [...course.assignedUsers, userId] }
            : course
        )
      );
    } catch (err) {
      alert("Error assigning user");
    }
  };

  const getVisibleCourses = () => {
    if (!currentUser) return [];

    switch (currentUser.role) {
      case "SUPER_ADMIN":
        return courses; // See all courses
      case "ADMIN":
        return courses.filter((course) => course.adminId === currentUser.id); // See only assigned course
      case "USER":
        return courses.filter((course) => course.assignedUsers.includes(currentUser.id)); // See only enrolled courses
      default:
        return [];
    }
  };

  return (
    <div className="p-6 min-h-screen bg-gray-900 text-white">
      <div className="flex justify-between items-center mb-6">
        <div>
          <h1 className="text-2xl font-semibold">Course Management</h1>
          <p className="text-sm text-gray-400">Logged in as: {currentUser?.role}</p>
        </div>

        <div className="flex gap-4">
          {currentUser?.role === "SUPER_ADMIN" && (
            <div className="flex items-center gap-2">
              <input
                className="px-4 py-2 bg-gray-800 text-white border border-gray-700 rounded"
                value={newCourseTitle}
                onChange={(e) => setNewCourseTitle(e.target.value)}
                placeholder="Enter course name"
              />
              <button
                className="px-4 py-2 bg-purple-600 hover:bg-purple-700 text-white rounded transition"
                onClick={handleAddCourse}
              >
                <Plus className="w-4 h-4 inline-block mr-2" /> Add Course
              </button>
            </div>
          )}

          <button
            onClick={handleLogout}
            className="px-4 py-2 bg-red-600 hover:bg-red-700 text-white rounded transition"
          >
            Logout
          </button>
        </div>
      </div>

      {error && <p className="mb-4 text-red-400">{error}</p>}

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {getVisibleCourses().map((course) => (
          <div
            key={course.id}
            className="p-6 bg-gray-800 rounded-lg shadow-lg border border-gray-700 hover:shadow-xl transition"
          >
            <div className="flex justify-between">
              <h2 className="text-lg font-medium">{course.title}</h2>
              <div className="flex gap-2">
                {currentUser?.role === "SUPER_ADMIN" && (
                  <button
                    className="p-2 text-gray-400 hover:text-white transition"
                    onClick={() => assignAdminToCourse(course.id, prompt("Enter Admin ID"))}
                  >
                    <UserPlus className="w-4 h-4" />
                  </button>
                )}
                {(currentUser?.role === "SUPER_ADMIN" ||
                  (currentUser?.role === "ADMIN" && course.adminId === currentUser.id)) && (
                  <>
                    <button
                      className="p-2 text-gray-400 hover:text-white transition"
                      onClick={() => assignUserToCourse(course.id, prompt("Enter User ID"))}
                    >
                      <Pencil className="w-4 h-4" />
                    </button>
                    <button
                      onClick={() => handleDeleteCourse(course.id)}
                      className="p-2 text-red-500 hover:text-red-700 transition"
                    >
                      <Trash2 className="w-4 h-4" />
                    </button>
                  </>
                )}
              </div>
            </div>
            <p className="text-sm text-gray-400 mt-2">{course.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CourseManagement;
