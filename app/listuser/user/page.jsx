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

  const handleAssignAdmin = () => {
    // Add your admin assignment logic here
    console.log("Assigning admin role");
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold text-gray-800">User Management</h1>
        <div className="flex gap-4">
          <button 
            onClick={handleAssignAdmin}
            className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600 transition-colors"
          >
            Assign as Admin
          </button>
          <button 
            onClick={() => window.location.href = '/dashboard'}
            className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition-colors"
          >
            Back to Dashboard
          </button>
        </div>
      </div>

      {error && (
        <div className="mb-4 p-4 bg-red-50 border border-red-200 rounded text-red-600">
          {error}
        </div>
      )}

      <div className="bg-white rounded-lg shadow overflow-hidden">
        <table className="min-w-full">
          <thead>
            <tr className="bg-blue-500 text-white">
              <th className="px-6 py-3 text-left">ID</th>
              <th className="px-6 py-3 text-left">Username</th>
              <th className="px-6 py-3 text-left">Email</th>
              <th className="px-6 py-3 text-left">Roles</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {loading ? (
              // Loading skeleton
              [...Array(5)].map((_, index) => (
                <tr key={index} className="animate-pulse">
                  <td className="px-6 py-4"><div className="h-4 bg-gray-200 rounded w-24"></div></td>
                  <td className="px-6 py-4"><div className="h-4 bg-gray-200 rounded w-20"></div></td>
                  <td className="px-6 py-4"><div className="h-4 bg-gray-200 rounded w-32"></div></td>
                  <td className="px-6 py-4"><div className="h-4 bg-gray-200 rounded w-16"></div></td>
                </tr>
              ))
            ) : users.length > 0 ? (
              users.map((user) => (
                <tr key={user.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4 text-sm text-gray-500">
                    {user.id}
                  </td>
                  <td className="px-6 py-4">
                    {user.username}
                  </td>
                  <td className="px-6 py-4">
                    {user.email}
                  </td>
                  <td className="px-6 py-4">
                    <span className="px-2 py-1 text-sm bg-gray-100 rounded">
                      {user.roles.map(role => role.name).join(", ")}
                    </span>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
<<<<<<< HEAD
                <td colSpan="4" className="px-6 py-4 text-center text-gray-500">
=======
                <td colSpan="5" className="text-center text-gray-500 p-4">
>>>>>>> 79be621553994ab54de779fb60683b354ab3714c
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
