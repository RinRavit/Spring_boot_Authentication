// "use client";

// import { useState, useEffect } from "react";
// import API from "../../utils/api";

// export default function ListUser() {
//   const [users, setUsers] = useState([]);
//   const [error, setError] = useState("");
//   const [loading, setLoading] = useState(true);

//   useEffect(() => {
//     const fetchUsers = async () => {
//       try {
//         const response = await API.get("/superadmin/list-users");
//         setUsers(response.data);
//       } catch (error) {
//         console.error("Error fetching users:", error.response?.data || error.message);
//         setError("Failed to fetch user data.");
//       } finally {
//         setLoading(false);
//       }
//     };

//     fetchUsers();
//   }, []);

//   // Function to get initials from username
//   const getInitials = (username) => {
//     return username
//       .split(' ')
//       .map(word => word[0])
//       .join('')
//       .toUpperCase()
//       .slice(0, 2);
//   };

//   if (loading) {
//     return (
//       <div className="p-6">
//         <div className="bg-white rounded-lg shadow">
//           <div className="p-4 border-b">
//             <h2 className="text-xl font-semibold">User Management</h2>
//           </div>
//           <div className="p-4">
//             {[1, 2, 3].map((i) => (
//               <div key={i} className="animate-pulse flex items-center space-x-4 py-3">
//                 <div className="h-12 w-12 rounded-full bg-gray-200"></div>
//                 <div className="space-y-2 flex-1">
//                   <div className="h-4 bg-gray-200 rounded w-1/4"></div>
//                   <div className="h-4 bg-gray-200 rounded w-1/3"></div>
//                 </div>
//               </div>
//             ))}
//           </div>
//         </div>
//       </div>
//     );
//   }

//   return (
//     <div className="p-6">
//       <div className="bg-white rounded-lg shadow">
//         <div className="p-4 border-b">
//           <h2 className="text-xl font-semibold">User Management</h2>
//         </div>
        
//         <div className="p-4">
//           {error && (
//             <div className="mb-4 p-4 bg-red-50 border border-red-200 rounded text-red-600">
//               {error}
//             </div>
//           )}

//           {users.length > 0 ? (
//             <div className="space-y-3">
//               {users.map((user) => (
//                 <div
//                   key={user.id}
//                   className="flex items-center justify-between p-4 rounded-lg border hover:bg-gray-50 transition-colors"
//                 >
//                   <div className="flex items-center space-x-4">
//                     <div className="w-12 h-12 rounded-full bg-blue-100 flex items-center justify-center text-blue-600 font-medium">
//                       {getInitials(user.username)}
//                     </div>
//                     <div>
//                       <div className="font-medium">{user.username}</div>
//                       <div className="text-sm text-gray-500">{user.email}</div>
//                     </div>
//                   </div>
//                   <div className="flex gap-2">
//                     {user.roles.map((role, index) => (
//                       <span
//                         key={index}
//                         className="px-3 py-1 rounded-full text-sm bg-gray-100 text-gray-600"
//                       >
//                         {role.name}
//                       </span>
//                     ))}
//                   </div>
//                 </div>
//               ))}
//             </div>
//           ) : (
//             <div className="text-center py-8 text-gray-500">
//               <div className="w-16 h-16 mx-auto mb-4 rounded-full bg-gray-100 flex items-center justify-center">
//                 <svg
//                   className="w-8 h-8 text-gray-400"
//                   fill="none"
//                   strokeLinecap="round"
//                   strokeLinejoin="round"
//                   strokeWidth="2"
//                   viewBox="0 0 24 24"
//                   stroke="currentColor"
//                 >
//                   <path d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
//                 </svg>
//               </div>
//               <p>No users found.</p>
//             </div>
//           )}
//         </div>
//       </div>
//     </div>
//   );
// // }
// "use client";

// import { useState, useEffect } from "react";
// import API from "../../utils/api";
// import { useRouter } from "next/navigation";

// export default function ListUser() {
//   const [users, setUsers] = useState([]);
//   const [error, setError] = useState("");
//   const [loading, setLoading] = useState(true);
//   const router = useRouter();

//   useEffect(() => {
//     const fetchUsers = async () => {
//       try {
//         const response = await API.get("/superadmin/list-users");
//         setUsers(response.data);
//       } catch (error) {
//         console.error("Error fetching users:", error.response?.data || error.message);
//         setError("Failed to fetch user data.");
//       } finally {
//         setLoading(false);
//       }
//     };

//     fetchUsers();
//   }, []);

//   const handleAssignRole = (userId) => {
//     router.push(`/course/assign?userId=${userId}`); // Navigate to Assign Role Page with userId
//   };

//   // Function to get initials from username
//   const getInitials = (username) => {
//     return username
//       .split(' ')
//       .map(word => word[0])
//       .join('')
//       .toUpperCase()
//       .slice(0, 2);
//   };

//   if (loading) {
//     return (
//       <div className="p-6">
//         <div className="bg-white rounded-lg shadow">
//           <div className="p-4 border-b">
//             <h2 className="text-xl font-semibold">User Management</h2>
//           </div>
//           <div className="p-4">
//             {[1, 2, 3].map((i) => (
//               <div key={i} className="animate-pulse flex items-center space-x-4 py-3">
//                 <div className="h-12 w-12 rounded-full bg-gray-200"></div>
//                 <div className="space-y-2 flex-1">
//                   <div className="h-4 bg-gray-200 rounded w-1/4"></div>
//                   <div className="h-4 bg-gray-200 rounded w-1/3"></div>
//                 </div>
//               </div>
//             ))}
//           </div>
//         </div>
//       </div>
//     );
//   }

//   return (
//     <div className="p-6">
//       <div className="bg-white rounded-lg shadow">
//         <div className="p-4 border-b">
//           <h2 className="text-xl font-semibold">User Management</h2>
//         </div>
        
//         <div className="p-4">
//           {error && (
//             <div className="mb-4 p-4 bg-red-50 border border-red-200 rounded text-red-600">
//               {error}
//             </div>
//           )}

//           {users.length > 0 ? (
//             <div className="space-y-3">
//               {users.map((user) => (
//                 <div
//                   key={user.id}
//                   className="flex items-center justify-between p-4 rounded-lg border hover:bg-gray-50 transition-colors"
//                 >
//                   <div className="flex items-center space-x-4">
//                     <div className="w-12 h-12 rounded-full bg-blue-100 flex items-center justify-center text-blue-600 font-medium">
//                       {getInitials(user.username)}
//                     </div>
//                     <div>
//                       <div className="font-medium">{user.username}</div>
//                       <div className="text-sm text-gray-500">{user.email}</div>
//                     </div>
//                   </div>
//                   <div className="flex gap-2">
//                     {user.roles.map((role, index) => (
//                       <span
//                         key={index}
//                         className="px-3 py-1 rounded-full text-sm bg-gray-100 text-gray-600"
//                       >
//                         {role.name}
//                       </span>
//                     ))}
//                   </div>
//                   {/* Assign Admin Button */}
//                   <button
//                     onClick={() => handleAssignRole(user.id)}
//                     className="px-3 py-1 bg-blue-500 text-white rounded hover:bg-blue-600"
//                   >
//                     Assign as Admin
//                   </button>
//                 </div>
//               ))}
//             </div>
//           ) : (
//             <div className="text-center py-8 text-gray-500">
//               <div className="w-16 h-16 mx-auto mb-4 rounded-full bg-gray-100 flex items-center justify-center">
//                 <svg
//                   className="w-8 h-8 text-gray-400"
//                   fill="none"
//                   strokeLinecap="round"
//                   strokeLinejoin="round"
//                   strokeWidth="2"
//                   viewBox="0 0 24 24"
//                   stroke="currentColor"
//                 >
//                   <path d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
//                 </svg>
//               </div>
//               <p>No users found.</p>
//             </div>
//           )}
//         </div>
//       </div>
//     </div>
//   );
// }


"use client";

import { useState, useEffect } from "react";
import API from "../../utils/api";
import { useRouter } from "next/navigation";

export default function ListUser() {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await API.get("/superadmin/list-users");
        setUsers(response.data);
      } catch (error) {
        console.error("Error fetching users:", error.response?.data || error.message);
        setError("Failed to fetch user data.");
      } finally {
        setLoading(false);
      }
    };

    fetchUsers();
  }, []);

  const handleAssignRole = (userId) => {
    router.push(`/course/assign?userId=${userId}`); // Navigate to Assign Role Page with userId
  };

  if (loading) {
    return (
      <div className="p-6 flex justify-center items-center min-h-screen">
        <div className="text-gray-500">Loading users...</div>
      </div>
    );
  }

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold text-gray-800">User Management</h1>
        <button
          onClick={() => router.push("/dashboard/superadmin")}
          className="bg-blue-500 text-white px-4 py-2 rounded"
        >
          Back to Dashboard
        </button>
      </div>

      <div className="bg-white shadow-md rounded">
        <table className="min-w-full border-collapse border border-gray-200">
          <thead className="bg-blue-500 text-white">
            <tr>
              <th className="p-2 border border-gray-200">ID</th>
              <th className="p-2 border border-gray-200">Username</th>
              <th className="p-2 border border-gray-200">Email</th>
              <th className="p-2 border border-gray-200">Roles</th>
              <th className="p-2 border border-gray-200">Actions</th>
            </tr>
          </thead>
          <tbody>
            {users.length > 0 ? (
              users.map((user) => (
                <tr key={user.id} className="hover:bg-gray-100 text-gray-800">
                  <td className="p-2 border border-gray-200">{user.id}</td>
                  <td className="p-2 border border-gray-200">{user.username}</td>
                  <td className="p-2 border border-gray-200">{user.email}</td>
                  <td className="p-2 border border-gray-200">
                    {user.roles.map((role, index) => (
                      <span
                        key={index}
                        className="px-3 py-1 rounded-full text-sm bg-gray-100 text-gray-600 mr-1"
                      >
                        {role.name}
                      </span>
                    ))}
                  </td>
                  <td className="p-2 border border-gray-200">
                    <button
                      onClick={() => handleAssignRole(user.id)}
                      className="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600"
                    >
                      Assign as Admin
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="5" className="text-center text-gray-500 p-4">
                  No users found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {error && (
        <div className="mt-4 p-4 bg-red-50 border border-red-200 rounded text-red-600">
          {error}
        </div>
      )}
    </div>
  );
}
