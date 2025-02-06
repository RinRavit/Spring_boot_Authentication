// "use client";

// import { useEffect, useState } from "react";
// import API from "../../utils/api";

// export default function SuperAdminDashboard() {
//   const [stats, setStats] = useState({
//     users: 0,
//     admins: 0,
//     courses: 0,
//   });
//   const [menuOpen, setMenuOpen] = useState(true);

//   useEffect(() => {
//     const fetchStats = async () => {
//       try {
//         const [userRes, adminRes, courseRes] = await Promise.all([
//           API.get("/superadmin/users"), // Endpoint to fetch all users
//           API.get("/superadmin/admins"), // Endpoint to fetch all admins
//           API.get("/courses/all-courses"), // Endpoint to fetch all courses
//         ]);

//         setStats({
//           users: userRes.data.length,
//           admins: adminRes.data.length,
//           courses: courseRes.data.length,
//         });
//       } catch (error) {
//         console.error("Error fetching stats:", error);
//       }
//     };

//     fetchStats();
//   }, []);

//   const handleLogout = () => {
//     localStorage.removeItem("token");
//     window.location.href = "/login";
//   };

//   return (
//     <div className="min-h-screen flex bg-gray-100">
//       {/* Sidebar */}
//       <div
//         className={`${
//           menuOpen ? "w-64" : "w-20"
//         } bg-blue-900 text-white transition-all duration-300`}
//       >
//         <div className="p-4 flex items-center">
//           <button
//             onClick={() => setMenuOpen(!menuOpen)}
//             className="focus:outline-none text-white"
//           >
//             ☰
//           </button>
//           <h1 className={`ml-4 text-lg font-bold ${menuOpen ? "block" : "hidden"}`}>
//             Spring boot
//           </h1>
//         </div>
//         <div className="space-y-4 p-4">
//           <a href="#" className="block text-gray-300 hover:text-white">
//             Course
//           </a>
//           <a href="#" className="block text-gray-300 hover:text-white">
//             List User
//           </a>
//           <a href="#" className="block text-gray-300 hover:text-white">
//           </a>
         
//         </div>
//       </div>

//       {/* Main Content */}
//       <div className="flex-1">
//         {/* Header */}
//         <div className="flex justify-between items-center p-4 bg-green-600 text-white">
//           <h1 className="text-xl font-bold">Dashboard</h1>
//           <div className="flex items-center space-x-4">
//             <span>Hello SuperAdmin </span>
//             <button
//               onClick={handleLogout}
//               className="bg-white text-green-600 px-4 py-1 rounded"
//             >
//               Logout
//             </button>
//           </div>
//         </div>

//         {/* Content */}
//         <div className="p-6 space-y-6">
//           {/* Statistics Boxes */}
//           <div className="grid grid-cols-3 gap-4">
//             <div className="bg-blue-500 text-white p-6 rounded shadow">
//               <h2 className="text-xl font-bold">Total Users</h2>
//               <p className="text-3xl mt-2">{stats.users}</p>
//             </div>
//             <div className="bg-red-500 text-white p-6 rounded shadow">
//               <h2 className="text-xl font-bold">Total Admins</h2>
//               <p className="text-3xl mt-2">{stats.admins}</p>
//             </div>
//             <div className="bg-green-500 text-white p-6 rounded shadow">
//               <h2 className="text-xl font-bold">Total Courses</h2>
//               <p className="text-3xl mt-2">{stats.courses}</p>
//             </div>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// }

"use client";

import { useState, useEffect } from "react";
import API from "../../utils/api";
import { useRouter } from "next/navigation";

export default function SuperAdminDashboard() {
  const [stats, setStats] = useState({
    users: 0,
    admins: 0,
    courses: 0,
  });
  const router = useRouter();
  const [menuOpen, setMenuOpen] = useState(true);

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const statsRes = await API.get("/courses/stats");
        const courseRes = await API.get("/courses/all-courses");

        setStats({
          users: statsRes.data.totalUsers,
          admins: statsRes.data.totalAdmins,
          courses: courseRes.data.length,
        });
      } catch (error) {
        console.error("Error fetching stats:", error);
      }
    };

    fetchStats();
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.href = "/login";
  };

  return (
    <div className="min-h-screen flex bg-gray-100">
      {/* Sidebar */}
      <div
        className={`${
          menuOpen ? "w-64" : "w-20"
        } bg-blue-900 text-white transition-all duration-300`}
      >
        <div className="p-4 flex items-center">
          <button
            onClick={() => setMenuOpen(!menuOpen)}
            className="focus:outline-none text-white"
          >
            ☰
          </button>
          <h1 className={`ml-4 text-lg font-bold ${menuOpen ? "block" : "hidden"}`}>
            Spring Boot
          </h1>
        </div>
        <div className="space-y-4 p-4">
          {/* Navigate to Courses */}
          <button
  onClick={() => router.push("/course")}
  className="block text-gray-300 hover:text-white"
>
  Courses
</button>
          <a href="#" className="block text-gray-300 hover:text-white">
            List User
          </a>
        </div>
      </div>

      {/* Main Content */}
      <div className="flex-1">
        {/* Header */}
        <div className="flex justify-between items-center p-4 bg-green-600 text-white">
          <h1 className="text-xl font-bold">Dashboard</h1>
          <div className="flex items-center space-x-4">
            <span>Hello SuperAdmin</span>
            <button
              onClick={handleLogout}
              className="bg-white text-green-600 px-4 py-1 rounded"
            >
              Logout
            </button>
          </div>
        </div>

        {/* Content */}
        <div className="p-6 space-y-6">
          {/* Statistics Boxes */}
          <div className="grid grid-cols-3 gap-4">
            <div className="bg-blue-500 text-white p-6 rounded shadow">
              <h2 className="text-xl font-bold">Total Users</h2>
              <p className="text-3xl mt-2">{stats.users}</p>
            </div>
            <div className="bg-red-500 text-white p-6 rounded shadow">
              <h2 className="text-xl font-bold">Total Admins</h2>
              <p className="text-3xl mt-2">{stats.admins}</p>
            </div>
            <div className="bg-green-500 text-white p-6 rounded shadow">
              <h2 className="text-xl font-bold">Total Courses</h2>
              <p className="text-3xl mt-2">{stats.courses}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
