"use client";

import { useState, useEffect } from "react";
import API from "../../utils/api"; // Adjust path to API utility
import { useRouter } from "next/navigation";

export default function ListAdmin() {
  const [admins, setAdmins] = useState([]);
  const router = useRouter();

  useEffect(() => {
    const fetchAdmins = async () => {
      try {
        const response = await API.get("/superadmin/list-admins"); // Backend API to fetch admins
        setAdmins(response.data);
      } catch (error) {
        console.error("Error fetching admins:", error);
      }
    };

    fetchAdmins();
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <h1 className="text-2xl font-bold mb-4">List of Admins</h1>
      <button
        onClick={() => router.push("/dashboard/superadmin")}
        className="mb-4 bg-blue-500 text-white px-4 py-2 rounded"
      >
        Back to Dashboard
      </button>
      <div className="bg-white shadow-md rounded">
        <table className="min-w-full border-collapse border border-gray-200">
          <thead className="bg-blue-500 text-white">
            <tr>
              <th className="p-2 border border-gray-200">ID</th>
              <th className="p-2 border border-gray-200">Username</th>
              <th className="p-2 border border-gray-200">Email</th>
              <th className="p-2 border border-gray-200">Actions</th>
            </tr>
          </thead>
          <tbody>
            {admins.map((admin) => (
              <tr key={admin.id} className="hover:bg-gray-100">
                <td className="p-2 border border-gray-200">{admin.id}</td>
                <td className="p-2 border border-gray-200">{admin.username}</td>
                <td className="p-2 border border-gray-200">{admin.email}</td>
                <td className="p-2 border border-gray-200">
                  <button
                    onClick={() => alert(`View profile of ${admin.username}`)}
                    className="bg-green-500 text-white px-2 py-1 rounded"
                  >
                    View
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
