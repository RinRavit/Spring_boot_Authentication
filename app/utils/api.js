import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080", // Spring Boot backend URL
  withCredentials: true,
});

// Automatically add the JWT token to headers if it exists
API.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default API;
