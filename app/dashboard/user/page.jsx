import { withRoleProtection } from '../../utils/auth';

const UserDashboard = () => {
  return <div>Welcome to the USER Dashboard!</div>;
};

export default withRoleProtection(UserDashboard, 'USER');
