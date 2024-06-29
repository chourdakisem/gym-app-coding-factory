import '../styles/Login.css';
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { handleLogin } from "../services/auth";

const Login = ({userData, setUserData}) => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    return (
        <main className="main">
            <form className="flex-col form" >
                <h2 className="title">Login Form</h2>
                <div className="username">
                    <input 
                        id="username" 
                        type="text" 
                        placeholder="Username"
                        value={username}
                        onChange={(event) => setUsername(event.target.value)} 
                    />
                </div>
                <div className="password">
                    <input 
                        id="password" 
                        type="password" 
                        placeholder="Password" 
                        value={password}
                        onChange={(event) => setPassword(event.target.value)}
                    />
                    <p className="error">{error?.message}</p>
                </div>
                <div className="btn">
                    <button 
                        type="submit"
                        onClick={(event) => handleLogin(event, username, password, setUserData, navigate, setError)}
                    >Login</button>
                </div>
                <div>
                    <span>Not a user? </span>
                    <Link className="link" to="/register">Sign up</Link>
                </div>
            </form>
        </main>
    );
};

export default Login;