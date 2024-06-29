import { FaHouse, FaAlignJustify, FaUser, FaArrowRightFromBracket } from "react-icons/fa6";
import { Link, Outlet } from 'react-router-dom';
import '../styles/Layout.css';
import { handleLogout } from "../services/auth";
import { Navigate, useNavigate } from "react-router-dom";
import { handleRefresh } from "../services/auth";
import { useEffect } from "react";

const Layout = ({setUserData, userData}) => {

    const navigate = useNavigate();
    const isAuthenticated = localStorage.getItem("accessToken");

    useEffect(() => {
        (async () => {
            await handleRefresh(setUserData);
        })();
    }, []);

    if (!isAuthenticated) {
        return <Navigate to="/login" />;
    }

    return (
        <div className="flex-row">
            <div className="sidebar">
                <h1 className="title">Gym App</h1>
                <hr />
                <div className="navbar">
                    <Link to="/" className="listItem" title="Home"><FaHouse /><span>Home</span></Link>
                    <Link to="/schedule" className="listItem" title="Schedule"><FaAlignJustify /><span>Schedule</span></Link>
                    <Link to="/profile" className="listItem" title="Profile"><FaUser /><span>Profile</span></Link>
                    <Link to="/login" className="listItem" title="Logout" onClick={() => handleLogout(setUserData)}><FaArrowRightFromBracket /><span>Logout</span></Link>
                </div>
            </div>
            <main className="main-content">
                <Outlet />
            </main>
        </div>
    );
};

export default Layout;