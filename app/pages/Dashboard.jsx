import { useEffect, useState } from "react";
import API from "../utils/api";

export default function Dashboard() {
  const [data, setData] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await API.get("/admin/users"); // Admin endpoint
        setData(response.data);
      } catch (err) {
        setError("You are not authorized to view this page.");
      }
    };

    fetchData();
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.href = "/login";
  };

  return (
    <div>
    <h1>Dashboard</h1>
    <button onClick={handleLogout}>Logout</button>
    {error ? (
      <p style={{ color: "red" }}>{error}</p>
    ) : (
      <ul>
        {data.map((user) => (
          <li key={user.id}>{user.username}</li>
        ))}
      </ul>
    )}
  </div>
  );
}
