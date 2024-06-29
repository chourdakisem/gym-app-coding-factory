import { Link } from 'react-router-dom';
import { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import { handleRegister } from '../services/auth';
import '../styles/Register.css';

const Register = () => {

    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("");
    const [user, setUser] = useState(null);
    const [error, setError] = useState("");

    const navigate = useNavigate();

    
    const handleClick = async (event) => {
        event.preventDefault();
        setUser({
            firstname,
            lastname,
            email,
            username,
            password, 
            role
        });
        await handleRegister(user, navigate, setError);
    };

    useEffect(() => {
    }, [role]);

    return (
        <main className="main">
            <form className="flex-col form" >
                <h2 className="title">Register Form</h2>
                <div className="firstname">
                    <input 
                        id="firstname" 
                        type="text" 
                        placeholder="FirstName" 
                        value = {firstname}
                        onChange = {(event) => setFirstname(event.target.value)}
                    />
                </div>
                <div className="lastname">
                    <input 
                        id="lastname" 
                        type="text" 
                        placeholder="Lastname" 
                        value = {lastname}
                        onChange = {(event) => setLastname(event.target.value)}
                    />
                </div>
                <div className="email">
                    <input 
                        id="email" 
                        type="email" 
                        placeholder="Email" 
                        value = {email}
                        onChange = {(event) => setEmail(event.target.value)}
                    />
                </div>
                <div className="username">
                    <input 
                        id="username" 
                        type="text" 
                        placeholder="Username" 
                        value = {username}
                        onChange = {(event) => setUsername(event.target.value)}
                    />
                </div>
                <div className="password">
                    <input 
                        id="password" 
                        type="password" 
                        placeholder="Password"
                        value = {password}
                        onChange = {(event) => setPassword(event.target.value)} 
                    />
                </div>
                <fieldset className="role-wrapper">
                    <legend>Are you a trainer or a user?</legend>
                    <div className="role">
                        <div>
                            <input 
                                id="trainer"
                                type="radio" 
                                name="role" 
                                checked={role === "TRAINER"}
                                value="TRAINER"
                                onChange={(event) => setRole(event.target.value)}
                            />    
                            <label htmlFor="trainer"> Trainer </label> 
                        </div>
                        <div>
                            <input 
                                id="client" 
                                type="radio" 
                                name="role" 
                                checked={role === "CLIENT"}
                                value="CLIENT"
                                onChange={(event) => setRole(event.target.value)}
                            />
                            <label htmlFor="client"> User</label> 
                        </div>
                    </div>
                </fieldset>
                <div className="error">{error}</div>
                <div className="btn">
                    <button 
                        type="submit"
                        onClick={(event) => handleClick(event)}
                    >Register</button>
                </div>
                <div>
                    <span>Already a user? </span>
                    <Link className="link" to="/login">Sign in</Link>
                </div>
            </form>
        </main>
    );
};

export default Register;