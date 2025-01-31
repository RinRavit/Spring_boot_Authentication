import Link from "next/link";

export default function Home() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-900 text-white">
      <h1 className="text-3xl font-bold mb-4">Welcome to the App</h1>
      
      <div className="flex gap-4">
        <Link href="/login">
          <button className="px-9 py-2 bg-purple-500 rounded-lg hover:bg-purple-600 transition">
            Login
          </button>
        </Link>

        <Link href="/register">
          <button className="px-6 py-2 bg-purple-500 rounded-lg hover:bg-purple-600 transition">
            Register
          </button>
        </Link>
      </div>
    </div>
  );
}