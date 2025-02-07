"use client";

import { useState, useEffect } from "react";
import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080", // Backend URL
  withCredentials: true,
});

export default function AssignRolePage() {
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState("");
  const [selectedRole, setSelectedRole] = useState("ADMIN");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const token = localStorage.getItem("token");
        if (!token) {
          setError("Unauthorized. Please log in.");
          return;
        }

        const response = await API.get("/superadmin/list-users", {
          headers: { Authorization: `Bearer ${token}` },
        });

        setUsers(response.data);
      } catch (error) {
        console.error("Error fetching users:", error.response?.data || error.message);
        setError("Failed to load users.");
      }
    };

    fetchUsers();
  }, []);

  const handleAssignRole = async () => {
    if (!selectedUser) {
      alert("Please select a user.");
      return;
    }

    try {
      const token = localStorage.getItem("token");
      if (!token) {
        setError("Unauthorized. Please log in.");
        return;
      }

      const response = await API.post(
        "/superadmin/assign-role",
        { userId: selectedUser, roleName: selectedRole },
        { headers: { Authorization: `Bearer ${token}`, "Content-Type": "application/json" } }
      );

      setSuccess(response.data);
      setError("");
    } catch (error) {
      console.error("Error assigning role:", error.response?.data || error.message);
      setError(error.response?.data || "Failed to assign role.");
      setSuccess("");
    }
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen flex justify-center items-center">
      <div className="bg-white shadow-lg rounded p-6 w-full max-w-md">
        <h1 className="text-2xl font-bold mb-4 text-center">Assign Role</h1>

        {error && <div className="text-red-500 text-center mb-4">{error}</div>}
        {success && <div className="text-green-500 text-center mb-4">{success}</div>}

        <div className="mb-4">
          <label className="block text-gray-700 font-bold mb-2">Select User</label>
          <select
            className="w-full border px-4 py-2 rounded"
            value={selectedUser}
            onChange={(e) => setSelectedUser(e.target.value)}
          >
            <option value="">-- Select a User --</option>
            {users.map((user) => (
              <option key={user.id} value={user.id}>
                {user.username} ({user.email})
              </option>
            ))}
          </select>
        </div>

        <div className="mb-4">
          <label className="block text-gray-700 font-bold mb-2">Select Role</label>
          <select
            className="w-full border px-4 py-2 rounded"
            value={selectedRole}
            onChange={(e) => setSelectedRole(e.target.value)}
          >
            <option value="ADMIN">Admin</option>
            <option value="USER">User</option>
          </select>
        </div>

        <button
          onClick={handleAssignRole}
          className="bg-blue-500 text-white px-4 py-2 rounded w-full hover:bg-blue-600"
        >
          Assign Role
        </button>
      </div>
    </div>
  );
}
