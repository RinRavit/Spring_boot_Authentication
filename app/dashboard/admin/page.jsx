import { withRoleProtection } from '../../utils/auth';

const AdminDashboard = () => {
  return <div>Welcome to the ADMIN Dashboard!</div>;
};

export default withRoleProtection(AdminDashboard, 'ADMIN');
