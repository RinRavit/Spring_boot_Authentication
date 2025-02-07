// "use client";

// import { useState } from "react";
// import axios from "axios";

// const API = axios.create({
//   baseURL: "http://localhost:8080", // Backend URL
//   withCredentials: true,
// });

// export default function CreateCoursePage() {
//   const [course, setCourse] = useState({
//     name: "",
//     description: "",
//   });
//   const [error, setError] = useState("");
//   const [success, setSuccess] = useState("");

//   const handleInputChange = (e) => {
//     const { name, value } = e.target;
//     setCourse((prev) => ({
//       ...prev,
//       [name]: value,
//     }));
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     try {
//       const token = localStorage.getItem("token"); // Retrieve token from localStorage
  
//       if (!token) {
//         setError("Unauthorized. Please log in.");
//         return;
//       }
  
//       const response = await API.post("/courses/create", course, {
//         headers: {
//           Authorization: `Bearer ${token}`, // Send token with the request
//         },
//       });
  
//       setSuccess("Course created successfully!");
//       setCourse({ name: "", description: "" }); // Reset form
//       setError("");
//     } catch (error) {
//       console.error("Error creating course:", error.response?.data || error.message);
//       if (error.response?.status === 403) {
//         setError("You are not an admin. Only admins can create courses.");
//       } else {
//         setError("Failed to create course. Please try again.");
//       }
//       setSuccess("");
//     }
//   };
  


//   return (
//     <div className="p-6 bg-gray-100 min-h-screen">
//       <h1 className="text-2xl font-bold mb-6">Create New Course</h1>
//       <div className="bg-white shadow rounded p-6">
//         {error && <div className="text-red-500 mb-4">{error}</div>}
//         {success && <div className="text-green-500 mb-4">{success}</div>}
//         <form onSubmit={handleSubmit} className="space-y-4">
//           <div>
//             <label htmlFor="name" className="block text-gray-700 font-bold mb-2">
//               Course Name
//             </label>
//             <input
//               type="text"
//               id="name"
//               name="name"
//               value={course.name}
//               onChange={handleInputChange}
//               required
//               className="w-full border px-4 py-2 rounded"
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
//             ></textarea>
//           </div>
//           <button
//             type="submit"
//             className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
//           >
//             Create Course
//           </button>
//         </form>
//       </div>
//     </div>
//   );
// }

// "use client";

// import { useEffect, useState } from "react";
// import axios from "axios";
// import { useRouter } from "next/navigation"; // Import useRouter

// const API = axios.create({
//   baseURL: "http://localhost:8080", // Backend URL
//   withCredentials: true,
// });

// export default function CreateCoursePage() {
//   const [course, setCourse] = useState({ title: "", description: "" });
//   const [error, setError] = useState("");
//   const [success, setSuccess] = useState("");
//   const [userRole, setUserRole] = useState("");
//   const router = useRouter(); // Initialize router

//   // Fetch user role on page load
//   useEffect(() => {
//     const fetchUserRole = async () => {
//       try {
//         const token = localStorage.getItem("token");
//         if (!token) {
//           setError("Unauthorized. Please log in.");
//           return;
//         }

//         const response = await API.get("/auth/user-info", {
//           headers: { Authorization: `Bearer ${token}` },
//         });

//         setUserRole(response.data.role); // Backend should return { role: "SUPER_ADMIN" }
//       } catch (error) {
//         console.error("Error fetching user role:", error);
//         setError("Failed to fetch user info. Please log in.");
//       }
//     };

//     fetchUserRole();
//   }, []);

//   // Handle course form input changes
//   const handleInputChange = (e) => {
//     const { name, value } = e.target;
//     setCourse((prev) => ({
//       ...prev,
//       [name]: value,
//     }));
//   };

//   // Handle form submission
//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     try {
//       const token = localStorage.getItem("token");

//       if (!token) {
//         setError("Unauthorized. Please log in.");
//         return;
//       }

//       if (userRole !== "ADMIN" && userRole !== "SUPER_ADMIN") {
//         setError("You are not an admin. Only admins can create courses.");
//         return;
//       }

//       const response = await API.post("/courses/create", course, {
//         headers: { Authorization: `Bearer ${token}` },
//       });

//       setSuccess("Course created successfully!");
//       setError("");

//       // Redirect to course list page after 1.5 seconds
//       setTimeout(() => {
//         router.push("/course");
//       }, 1500);
//     } catch (error) {
//       console.error("Error creating course:", error.response?.data || error.message);
//       setError(
//         error.response?.status === 403
//           ? "You are not an admin. Only admins can create courses."
//           : "Failed to create course. Please try again."
//       );
//       setSuccess("");
//     }
//   };

//   return (
//     <div className="p-6 bg-gray-100 min-h-screen">
//       <h1 className="text-2xl font-bold mb-6">Create New Course</h1>
//       <div className="bg-white shadow rounded p-6">
//         {error && <div className="text-red-500 mb-4">{error}</div>}
//         {success && <div className="text-green-500 mb-4">{success}</div>}
//         <form onSubmit={handleSubmit} className="space-y-4">
//           <div>
//             <label htmlFor="title" className="block text-gray-700 font-bold mb-2">
//               Course Title
//             </label>
//             <input
//               type="text"
//               id="title"
//               name="title"
//               value={course.title}
//               onChange={handleInputChange}
//               required
//               className="w-full border px-4 py-2 rounded"
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
//             ></textarea>
//           </div>
//           <button
//             type="submit"
//             className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
//             disabled={userRole !== "ADMIN" && userRole !== "SUPER_ADMIN"} // Disable button if not an admin
//           >
//             Create Course
//           </button>
//         </form>
//       </div>
//     </div>
//   );
// }


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

"use client";

import { useState, useEffect } from "react";
import axios from "axios";
import { useRouter } from "next/navigation";

const API = axios.create({
  baseURL: "http://localhost:8080", // Backend URL
  withCredentials: true,
});

export default function CreateCoursePage() {
  const [course, setCourse] = useState({ title: "", description: "" });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [userRole, setUserRole] = useState("");
  const router = useRouter();

  useEffect(() => {
    // Get token and decode user role
    const token = localStorage.getItem("token");
    if (!token) {
      setError("Unauthorized. Please log in.");
      return;
    }

    try {
      const payload = JSON.parse(atob(token.split(".")[1])); // Decode JWT payload
      const roles = payload.roles || [];
      setUserRole(roles[0]); // Assuming first role is the primary one

      // Redirect if user is not ADMIN or SUPER_ADMIN
      if (!roles.includes("ADMIN") && !roles.includes("SUPER_ADMIN")) {
        setError("You do not have permission to create courses.");
        setTimeout(() => router.push("/course"), 2000);
      }
    } catch (e) {
      console.error("Error decoding token:", e);
      setError("Invalid token. Please log in again.");
    }
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCourse((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const token = localStorage.getItem("token");
      if (!token) {
        setError("Unauthorized. Please log in.");
        return;
      }

      const response = await API.post("/courses/create", course, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      });

      if (response.status === 201) {
        setSuccess("Course created successfully!");
        setCourse({ title: "", description: "" });

        setTimeout(() => router.push("/course"), 1000); // Redirect after success
      }
    } catch (error) {
      console.error("Error creating course:", error.response?.data || error.message);
      if (error.response?.status === 403) {
        setError("You are not an admin. Only admins and super admins can create courses.");
      } else {
        setError("Failed to create course. Please try again.");
      }
      setSuccess("");
    }
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen flex justify-center items-center">
      <div className="bg-white shadow-lg rounded p-6 w-full max-w-md">
        <h1 className="text-2xl font-bold mb-4 text-center">Create New Course</h1>

        {error && <div className="text-red-500 text-center mb-4">{error}</div>}
        {success && <div className="text-green-500 text-center mb-4">{success}</div>}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label htmlFor="title" className="block text-gray-700 font-bold mb-2">
              Course Title
            </label>
            <input
              type="text"
              id="title"
              name="title"
              value={course.title}
              onChange={handleInputChange}
              required
              className="w-full border px-4 py-2 rounded"
              placeholder="Enter course title"
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
              placeholder="Enter course description"
            ></textarea>
          </div>

          <button
            type="submit"
            className="bg-green-500 text-white px-4 py-2 rounded w-full hover:bg-green-600"
            disabled={userRole !== "ADMIN" && userRole !== "SUPER_ADMIN"}
          >
            Create Course
          </button>
        </form>
      </div>
    </div>
  );
}
