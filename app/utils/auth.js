import jwt from 'jsonwebtoken';

export const getRoleFromToken = (token) => {
  try {
    const decoded = jwt.decode(token);
    return decoded?.roles || [];
  } catch (error) {
    console.error("Invalid token", error);
    return [];
  }
};

export const withRoleProtection = (Component, requiredRole) => {
  return function ProtectedPage(props) {
    if (typeof window !== 'undefined') {
      const token = localStorage.getItem('authToken');
      const roles = token ? jwt.decode(token)?.roles : [];

      if (!roles.includes(requiredRole)) {
        window.location.href = '/login';
        return null;
      }
    }

    return <Component {...props} />;
  };
};
