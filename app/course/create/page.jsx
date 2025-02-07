"use client";

import { useState } from "react";
import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080", // Backend URL
  withCredentials: true,
});

export default function CreateCoursePage() {
  const [course, setCourse] = useState({
    name: "",
    description: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCourse((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await API.post("/courses/create", course);
      setSuccess("Course created successfully!");
      setCourse({ name: "", description: "" }); // Reset form
      setError("");
    } catch (error) {
      console.error("Error creating course:", error.response?.data || error.message);
      setError("Failed to create course. Please try again.");
      setSuccess("");
    }
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-2xl font-bold mb-6">Create New Course</h1>
      <div className="bg-white shadow rounded p-6">
        {error && <div className="text-red-500 mb-4">{error}</div>}
        {success && <div className="text-green-500 mb-4">{success}</div>}
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label htmlFor="name" className="block text-gray-700 font-bold mb-2">
              Course Name
            </label>
            <input
              type="text"
              id="name"
              name="name"
              value={course.name}
              onChange={handleInputChange}
              required
              className="w-full border px-4 py-2 rounded"
            />
          </div>
          <div>
            <label htmlFor="description" className="block text-gray-700 font-bold mb-2">
              Course Description
            </label>
            <textarea
              id="description"
              name="description"
              value={course.description}
              onChange={handleInputChange}
              required
              className="w-full border px-4 py-2 rounded"
            ></textarea>
          </div>
          <button
            type="submit"
            className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
          >
            Create Course
          </button>
        </form>
      </div>
    </div>
  );
}
