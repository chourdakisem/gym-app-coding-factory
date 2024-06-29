import { Navigate } from "react-router-dom"

const ProtectedRoute = ({children, user}) => {

    if (user?.role === "TRAINER") return <Navigate to="/schedule" />

    return children;
};

export default ProtectedRoute;