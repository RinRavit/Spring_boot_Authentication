// // "use client";

// // import { useState, useEffect } from "react";
// // // import API from "../utils/api";
// // const API ="http://localhost:8080";

// // export default function CoursesPage() {
// //   const [courses, setCourses] = useState([]);
// //   const [searchTerm, setSearchTerm] = useState("");
// //   const [page, setPage] = useState(1); // Current page
// //   const [pageSize, setPageSize] = useState(10); // Entries per page

// //   useEffect(() => {
// //     const fetchCourses = async () => {
// //       try {
// //         const response = await API.get("/courses/all-courses");
// //         setCourses(response.data);
// //       } catch (error) {
// //         console.error("Error fetching courses:", error);
// //       }
// //     };

// //     fetchCourses();
// //   }, []);

// //   const filteredCourses = courses.filter((course) =>
// //     course.name.toLowerCase().includes(searchTerm.toLowerCase())
// //   );

// //   const displayedCourses = filteredCourses.slice(
// //     (page - 1) * pageSize,
// //     page * pageSize
// //   );

// //   const handleDelete = async (id) => {
// //     if (confirm("Are you sure you want to delete this course?")) {
// //       try {
// //         await API.delete(`/courses/${id}`);
// //         setCourses(courses.filter((course) => course.id !== id));
// //       } catch (error) {
// //         console.error("Error deleting course:", error);
// //       }
// //     }
// //   };

// //   return (
// //     <div className="p-6 bg-gray-100 min-h-screen">
// //       <div className="flex justify-between items-center mb-6">
// //         <h1 className="text-2xl font-bold">Course List</h1>
// //         <button
// //           onClick={() => alert("Add New Course functionality not implemented!")}
// //           className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
// //         >
// //           + Add New Course
// //         </button>
// //       </div>

// //       <div className="bg-white shadow rounded p-4">
// //         {/* Controls */}
// //         <div className="flex justify-between items-center mb-4">
// //           <div>
// //             <label htmlFor="entries" className="mr-2">
// //               Show
// //             </label>
// //             <select
// //               id="entries"
// //               value={pageSize}
// //               onChange={(e) => setPageSize(Number(e.target.value))}
// //               className="border px-2 py-1 rounded"
// //             >
// //               {[5, 10, 20, 50].map((size) => (
// //                 <option key={size} value={size}>
// //                   {size}
// //                 </option>
// //               ))}
// //             </select>{" "}
// //             entries
// //           </div>
// //           <input
// //             type="text"
// //             placeholder="Search..."
// //             value={searchTerm}
// //             onChange={(e) => setSearchTerm(e.target.value)}
// //             className="border px-4 py-2 rounded"
// //           />
// //         </div>

// //         {/* Table */}
// //         <table className="w-full border-collapse border border-gray-200">
// //           <thead>
// //             <tr className="bg-gray-100">
// //               <th className="border p-2 text-left">#</th>
// //               <th className="border p-2 text-left">Course Name</th>
// //               <th className="border p-2 text-left">Description</th>
// //               <th className="border p-2 text-left">Actions</th>
// //             </tr>
// //           </thead>
// //           <tbody>
// //             {displayedCourses.length > 0 ? (
// //               displayedCourses.map((course, index) => (
// //                 <tr key={course.id} className="hover:bg-gray-100">
// //                   <td className="border p-2">{index + 1}</td>
// //                   <td className="border p-2">{course.name}</td>
// //                   <td className="border p-2">{course.description}</td>
// //                   <td className="border p-2">
// //                     <button
// //                       onClick={() => alert("Edit functionality not implemented!")}
// //                       className="bg-blue-500 text-white px-2 py-1 rounded mr-2 hover:bg-blue-600"
// //                     >
// //                       Edit
// //                     </button>
// //                     <button
// //                       onClick={() => handleDelete(course.id)}
// //                       className="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600"
// //                     >
// //                       Delete
// //                     </button>
// //                   </td>
// //                 </tr>
// //               ))
// //             ) : (
// //               <tr>
// //                 <td
// //                   colSpan="4"
// //                   className="text-center text-gray-500 p-4 border"
// //                 >
// //                   No courses available.
// //                 </td>
// //               </tr>
// //             )}
// //           </tbody>
// //         </table>

// //         {/* Pagination */}
// //         <div className="flex justify-between items-center mt-4">
// //           <div>
// //             Showing {Math.min((page - 1) * pageSize + 1, filteredCourses.length)}{" "}
// //             to {Math.min(page * pageSize, filteredCourses.length)} of{" "}
// //             {filteredCourses.length} entries
// //           </div>
// //           <div className="flex space-x-2">
// //             <button
// //               onClick={() => setPage((prev) => Math.max(prev - 1, 1))}
// //               disabled={page === 1}
// //               className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300 disabled:opacity-50"
// //             >
// //               Previous
// //             </button>
// //             <button
// //               onClick={() =>
// //                 setPage((prev) =>
// //                   Math.min(prev + 1, Math.ceil(filteredCourses.length / pageSize))
// //                 )
// //               }
// //               disabled={page === Math.ceil(filteredCourses.length / pageSize)}
// //               className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300 disabled:opacity-50"
// //             >
// //               Next
// //             </button>
// //           </div>
// //         </div>
// //       </div>
// //     </div>
// //   );
// // }
// "use client";

// import { useState, useEffect } from "react";
// import axios from "axios";

// const API = axios.create({
//   baseURL: "http://localhost:8080", // Backend URL
//   withCredentials: true,
// });

// // Automatically add the JWT token to headers if it exists
// API.interceptors.request.use((config) => {
//   const token = localStorage.getItem("token");
//   if (token) {
//     config.headers.Authorization = `Bearer ${token}`;
//   }
//   return config;
// });

// export default function CoursesPage() {
//   const [courses, setCourses] = useState([]);
//   const [searchTerm, setSearchTerm] = useState("");
//   const [page, setPage] = useState(1); // Current page
//   const [pageSize, setPageSize] = useState(10); // Entries per page

//   useEffect(() => {
//     const fetchCourses = async () => {
//       try {
//         const response = await API.get("/courses/all-courses");
//         console.log(response);
//         setCourses(response.data);
//       } catch (error) {
//         console.error("Error fetching courses:", error);
//       }
//     };

//     fetchCourses();
//   }, []);

//   const filteredCourses = courses.filter((course) =>
//     (course.name && course.name.toLowerCase().includes((searchTerm || "").toLowerCase()))
//   );

//   const displayedCourses = filteredCourses.slice(
//     (page - 1) * pageSize,
//     page * pageSize
//   );

//   const handleDelete = async (id) => {
//     if (confirm("Are you sure you want to delete this course?")) {
//       try {
//         await API.delete(`/courses/${id}`);
//         setCourses(courses.filter((course) => course.id !== id));
//       } catch (error) {
//         console.error("Error deleting course:", error);
//       }
//     }
//   };

//   return (
//     <div className="p-6 bg-gray-100 min-h-screen">
//       <div className="flex justify-between items-center mb-6">
//         <h1 className="text-2xl font-bold">Course List</h1>
//         <button
//           onClick={() => alert("Add New Course functionality not implemented!")}
//           className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
//         >
//           + Add New Course
//         </button>
//       </div>

//       <div className="bg-white shadow rounded p-4">
//         {/* Controls */}
//         <div className="flex justify-between items-center mb-4">
//           <div>
//             <label htmlFor="entries" className="mr-2">
//               Show
//             </label>
//             <select
//               id="entries"
//               value={pageSize}
//               onChange={(e) => setPageSize(Number(e.target.value))}
//               className="border px-2 py-1 rounded"
//             >
//               {[5, 10, 20, 50].map((size) => (
//                 <option key={size} value={size}>
//                   {size}
//                 </option>
//               ))}
//             </select>{" "}
//             entries
//           </div>
//           <input
//             type="text"
//             placeholder="Search..."
//             value={searchTerm}
//             onChange={(e) => setSearchTerm(e.target.value)}
//             className="border px-4 py-2 rounded"
//           />
//         </div>

//         {/* Table */}
//         <table className="w-full border-collapse border border-gray-200">
//           <thead>
//             <tr className="bg-gray-100">
//               <th className="border p-2 text-left">#</th>
//               <th className="border p-2 text-left">Course Name</th>
//               <th className="border p-2 text-left">Description</th>
//               <th className="border p-2 text-left">Actions</th>
//             </tr>
//           </thead>
//           <tbody>
//             {displayedCourses.length > 0 ? (
//               displayedCourses.map((course, index) => (
//                 <tr key={course.id} className="hover:bg-gray-100">
//                   <td className="border p-2">{index + 1}</td>
//                   <td className="border p-2">{course.name}</td>
//                   <td className="border p-2">{course.description}</td>
//                   <td className="border p-2">
//                     <button
//                       onClick={() => alert("Edit functionality not implemented!")}
//                       className="bg-blue-500 text-white px-2 py-1 rounded mr-2 hover:bg-blue-600"
//                     >
//                       Edit
//                     </button>
//                     <button
//                       onClick={() => handleDelete(course.id)}
//                       className="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600"
//                     >
//                       Delete
//                     </button>
//                   </td>
//                 </tr>
//               ))
//             ) : (
//               <tr>
//                 <td
//                   colSpan="4"
//                   className="text-center text-gray-500 p-4 border"
//                 >
//                   No courses available.
//                 </td>
//               </tr>
//             )}
//           </tbody>
//         </table>

//         {/* Pagination */}
//         <div className="flex justify-between items-center mt-4">
//           <div>
//             Showing {Math.min((page - 1) * pageSize + 1, filteredCourses.length)}{" "}
//             to {Math.min(page * pageSize, filteredCourses.length)} of{" "}
//             {filteredCourses.length} entries
//           </div>
//           <div className="flex space-x-2">
//             <button
//               onClick={() => setPage((prev) => Math.max(prev - 1, 1))}
//               disabled={page === 1}
//               className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300 disabled:opacity-50"
//             >
//               Previous
//             </button>
//             <button
//               onClick={() =>
//                 setPage((prev) =>
//                   Math.min(prev + 1, Math.ceil(filteredCourses.length / pageSize))
//                 )
//               }
//               disabled={page === Math.ceil(filteredCourses.length / pageSize)}
//               className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300 disabled:opacity-50"
//             >
//               Next
//             </button>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// }


// 'use client'
// import { useState, useEffect } from "react";
// import axios from "axios";

// const API = axios.create({
//   baseURL: "http://localhost:8080", // Ensure this matches your backend's base URL
//   withCredentials: true,
// });

// export default function CoursesPage() {
//   const [courses, setCourses] = useState([]);

//   useEffect(() => {
//     const fetchCourses = async () => {
//       try {
//         const response = await API.get("/courses/all-courses");
//         setCourses(response.data);
//       } catch (error) {
//         console.error("Error fetching courses:", error.response?.data || error.message);
//       }
//     };

//     fetchCourses();
//   }, []);

//   return (
//     <div>
//       <h1>Courses</h1>
//       <ul>
//         {courses.map((course) => (
//           <li key={course.id}>{course.name}</li>
//         ))}
//       </ul>
//     </div>
//   );
// }

//Work
// "use client";

// import { useState, useEffect } from "react";
// import axios from "axios";

// const API = axios.create({
//   baseURL: "http://localhost:8080", // Backend URL
//   withCredentials: true,
// });

// export default function CoursesPage() {
//   const [courses, setCourses] = useState([]);
//   const [searchTerm, setSearchTerm] = useState("");
//   const [page, setPage] = useState(1); // Current page
//   const [pageSize, setPageSize] = useState(10); // Entries per page

//   useEffect(() => {
//     const fetchCourses = async () => {
//       try {
//         const response = await API.get("/courses/all-courses");
//         setCourses(response.data);
//       } catch (error) {
//         console.error("Error fetching courses:", error.response?.data || error.message);
//       }
//     };

//     fetchCourses();
//   }, []);

//   // Filter courses based on search term
//   // const filteredCourses = courses.filter((course) =>
//   //   course.name.toLowerCase().includes(searchTerm.toLowerCase())
//   // );
//   // Filter courses based on search term
// const filteredCourses = courses.filter((course) =>
//   (course.name || "").toLowerCase().includes(searchTerm.toLowerCase())
// );


//   // Pagination logic
//   const displayedCourses = filteredCourses.slice(
//     (page - 1) * pageSize,
//     page * pageSize
//   );

//   const handleDelete = async (id) => {
//     if (confirm("Are you sure you want to delete this course?")) {
//       try {
//         await API.delete(`/courses/${id}`);
//         setCourses(courses.filter((course) => course.id !== id));
//       } catch (error) {
//         console.error("Error deleting course:", error.response?.data || error.message);
//       }
//     }
//   };

//   return (
//     <div className="p-6 bg-gray-100 min-h-screen">
//       <div className="flex justify-between items-center mb-6">
//         <h1 className="text-2xl font-bold">Course List</h1>
//         <button
//   onClick={() => router.push("/course/create")}
//   className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
// >
//   + Add New Course
// </button>
//       </div>

//       <div className="bg-white shadow rounded p-4">
//         {/* Controls */}
//         <div className="flex justify-between items-center mb-4">
//           <div>
//             <label htmlFor="entries" className="mr-2">
//               Show
//             </label>
//             <select
//               id="entries"
//               value={pageSize}
//               onChange={(e) => setPageSize(Number(e.target.value))}
//               className="border px-2 py-1 rounded"
//             >
//               {[5, 10, 20, 50].map((size) => (
//                 <option key={size} value={size}>
//                   {size}
//                 </option>
//               ))}
//             </select>{" "}
//             entries
//           </div>
//           <input
//             type="text"
//             placeholder="Search..."
//             value={searchTerm}
//             onChange={(e) => setSearchTerm(e.target.value)}
//             className="border px-4 py-2 rounded"
//           />
//         </div>

//         {/* Table */}
//         <table className="w-full border-collapse border border-gray-200">
//           <thead>
//             <tr className="bg-gray-100">
//               <th className="border p-2 text-left">#</th>
//               <th className="border p-2 text-left">Course Name</th>
//               <th className="border p-2 text-left">Description</th>
//               <th className="border p-2 text-left">Actions</th>
//             </tr>
//           </thead>
//           <tbody>
//             {displayedCourses.length > 0 ? (
//               displayedCourses.map((course, index) => (
//                 <tr key={course.id} className="hover:bg-gray-100">
//                   <td className="border p-2">{index + 1}</td>
//                   <td className="border p-2">{course.title}</td>
//                   <td className="border p-2">{course.description}</td>
//                   <td className="border p-2">
//                     <button
//                       onClick={() => alert("Edit functionality not implemented!")}
//                       className="bg-blue-500 text-white px-2 py-1 rounded mr-2 hover:bg-blue-600"
//                     >
//                       Edit
//                     </button>
//                     <button
//                       onClick={() => handleDelete(course.id)}
//                       className="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600"
//                     >
//                       Delete
//                     </button>
//                   </td>
//                 </tr>
//               ))
//             ) : (
//               <tr>
//                 <td
//                   colSpan="4"
//                   className="text-center text-gray-500 p-4 border"
//                 >
//                   No courses available.
//                 </td>
//               </tr>
//             )}
//           </tbody>
//         </table>

//         {/* Pagination */}
//         <div className="flex justify-between items-center mt-4">
//           <div>
//             Showing {Math.min((page - 1) * pageSize + 1, filteredCourses.length)}{" "}
//             to {Math.min(page * pageSize, filteredCourses.length)} of{" "}
//             {filteredCourses.length} entries
//           </div>
//           <div className="flex space-x-2">
//             <button
//               onClick={() => setPage((prev) => Math.max(prev - 1, 1))}
//               disabled={page === 1}
//               className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300 disabled:opacity-50"
//             >
//               Previous
//             </button>
//             <button
//               onClick={() =>
//                 setPage((prev) =>
//                   Math.min(prev + 1, Math.ceil(filteredCourses.length / pageSize))
//                 )
//               }
//               disabled={page === Math.ceil(filteredCourses.length / pageSize)}
//               className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300 disabled:opacity-50"
//             >
//               Next
//             </button>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// }





"use client";

import { useState, useEffect } from "react";
import axios from "axios";
import { useRouter } from "next/navigation";

const API = axios.create({
  baseURL: "http://localhost:8080", // Backend URL
  withCredentials: true,
});

export default function CoursesPage() {
  const [courses, setCourses] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [page, setPage] = useState(1); // Current page
  const [pageSize, setPageSize] = useState(10); // Entries per page
  const router = useRouter(); // Initialize router

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await API.get("/courses/all-courses");
        setCourses(response.data);
      } catch (error) {
        console.error("Error fetching courses:", error.response?.data || error.message);
      }
    };

    fetchCourses();
  }, []);

  const filteredCourses = courses.filter((course) =>
    (course.name || "").toLowerCase().includes(searchTerm.toLowerCase())
  );

  const displayedCourses = filteredCourses.slice(
    (page - 1) * pageSize,
    page * pageSize
  );

  const handleDelete = async (id) => {
    if (confirm("Are you sure you want to delete this course?")) {
      try {
        await API.delete(`/courses/${id}`);
        setCourses(courses.filter((course) => course.id !== id));
      } catch (error) {
        console.error("Error deleting course:", error.response?.data || error.message);
      }
    }
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Course List</h1>
        <button
        onClick={() => router.push("/dashboard/superadmin")}
        className="mb-4 bg-blue-500 text-white px-4 py-2 rounded"
      >
        Back to Dashboard
      </button>
        <button
          onClick={() => router.push("/course/create")}
          className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
        >
          + Add New Course
        </button>
      </div>

      <div className="bg-white shadow rounded p-4">
        {/* Controls */}
        <div className="flex justify-between items-center mb-4">
          <div>
            <label htmlFor="entries" className="mr-2">
              Show
            </label>
            <select
              id="entries"
              value={pageSize}
              onChange={(e) => setPageSize(Number(e.target.value))}
              className="border px-2 py-1 rounded"
            >
              {[5, 10, 20, 50].map((size) => (
                <option key={size} value={size}>
                  {size}
                </option>
              ))}
            </select>{" "}
            entries
          </div>
          <input
            type="text"
            placeholder="Search..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="border px-4 py-2 rounded"
          />
        </div>

        {/* Table */}
        <table className="w-full border-collapse border border-gray-200">
          <thead>
            <tr className="bg-gray-100">
              <th className="border p-2 text-left">#</th>
              <th className="border p-2 text-left">Course Name</th>
              <th className="border p-2 text-left">Description</th>
              <th className="border p-2 text-left">Actions</th>
            </tr>
          </thead>
          <tbody>
            {displayedCourses.length > 0 ? (
              displayedCourses.map((course, index) => (
                <tr key={course.id} className="hover:bg-gray-100">
                  <td className="border p-2">{index + 1}</td>
                  <td className="border p-2">{course.title}</td>
                  <td className="border p-2">{course.description}</td>
                  <td className="border p-2">
                    <button
                      onClick={() => alert("Edit functionality not implemented!")}
                      className="bg-blue-500 text-white px-2 py-1 rounded mr-2 hover:bg-blue-600"
                    >
                      Edit
                    </button>
                    <button
                      onClick={() => handleDelete(course.id)}
                      className="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td
                  colSpan="4"
                  className="text-center text-gray-500 p-4 border"
                >
                  No courses available.
                </td>
              </tr>
            )}
          </tbody>
        </table>

        {/* Pagination */}
        <div className="flex justify-between items-center mt-4">
          <div>
            Showing {Math.min((page - 1) * pageSize + 1, filteredCourses.length)}{" "}
            to {Math.min(page * pageSize, filteredCourses.length)} of{" "}
            {filteredCourses.length} entries
          </div>
          <div className="flex space-x-2">
            <button
              onClick={() => setPage((prev) => Math.max(prev - 1, 1))}
              disabled={page === 1}
              className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300 disabled:opacity-50"
            >
              Previous
            </button>
            <button
              onClick={() =>
                setPage((prev) =>
                  Math.min(prev + 1, Math.ceil(filteredCourses.length / pageSize))
                )
              }
              disabled={page === Math.ceil(filteredCourses.length / pageSize)}
              className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300 disabled:opacity-50"
            >
              Next
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}




// "use client";

// import { useState } from "react";
// import axios from "axios";
// import { useRouter } from "next/navigation";

// const API = axios.create({
//   baseURL: "http://localhost:8080", // Backend URL
//   withCredentials: true,
// });

// export default function CreateCoursePage() {
//   const [course, setCourse] = useState({ name: "", description: "" });
//   const [error, setError] = useState("");
//   const [success, setSuccess] = useState("");
//   const router = useRouter(); // For navigation

//   const handleInputChange = (e) => {
//     const { name, value } = e.target;
//     setCourse((prev) => ({ ...prev, [name]: value }));
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     console.log("Submit button clicked!"); // Debugging Step 1

//     try {
//       const token = localStorage.getItem("token"); // Retrieve token
//       if (!token) {
//         setError("Unauthorized. Please log in.");
//         return;
//       }

//       console.log("Token exists, proceeding with request..."); // Debugging Step 2

//       const response = await API.post("/courses/create", course, {
//         headers: {
//           Authorization: `Bearer ${token}`, // Send token with request
//           "Content-Type": "application/json"
//         },
//       });

//       console.log("Response received:", response); // Debugging Step 3

//       if (response.status === 201) {
//         setSuccess("Course created successfully!");
//         setCourse({ name: "", description: "" }); // Reset form

//         // Redirect to course list after 1 second
//         setTimeout(() => {
//           router.push("/course");
//         }, 1000);
//       }
//     } catch (error) {
//       console.error("Error creating course:", error.response?.data || error.message);
//       if (error.response?.status === 403) {
//         setError("You are not an admin. Only admins and super admins can create courses.");
//       } else {
//         setError(error.response?.data || "Failed to create course. Please try again.");
//       }
//       setSuccess("");
//     }
//   };

//   return (
//     <div className="p-6 bg-gray-100 min-h-screen flex justify-center items-center">
//       <div className="bg-white shadow-lg rounded p-6 w-full max-w-md">
//         <h1 className="text-2xl font-bold mb-4 text-center">Create New Course</h1>

//         {error && <div className="text-red-500 text-center mb-4">{error}</div>}
//         {success && <div className="text-green-500 text-center mb-4">{success}</div>}

//         <form onSubmit={handleSubmit} className="space-y-4">
//           <div>
//             <label htmlFor="name" className="block text-gray-700 font-bold mb-2">
//               Course Title
//             </label>
//             <input
//               type="text"
//               id="name"
//               name="name"
//               value={course.name}
//               onChange={handleInputChange}
//               required
//               className="w-full border px-4 py-2 rounded"
//               placeholder="Enter course title"
//             />
//           </div>

//           <div>
//             <label htmlFor="description" className="block text-gray-700 font-bold mb-2">
//               Course Description
//             </label>
//             <textarea
//               id="description"
//               name="description"
//               value={course.description}
//               onChange={handleInputChange}
//               required
//               className="w-full border px-4 py-2 rounded"
//               placeholder="Enter course description"
//             ></textarea>
//           </div>

//           <button
//             type="submit"
//             className="bg-green-500 text-white px-4 py-2 rounded w-full hover:bg-green-600"
//           >
//             Create Course
//           </button>
//         </form>
//       </div>
//     </div>
//   );
// }




