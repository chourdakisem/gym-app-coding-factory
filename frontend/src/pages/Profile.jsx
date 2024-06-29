import { useState } from "react";
import "../styles/Profile.css"
import TrainerProfile from "../components/TrainerProfile";
import { createClient } from "../services/util";
import { updateClient } from "../services/dataService";
import { handleRefresh } from "../services/auth";

const Profile = ({userData, setUserData}) => {

    const [firstname, setFirstname] = useState(userData.firstname);
    const [lastname, setLastname] = useState(userData.lastname);
    const [email, setEmail] = useState(userData.email);
    const [phone, setPhone] = useState(userData.phone);
    const [username, setUsername] = useState(userData.username);
    const [location, setLocation] = useState(userData?.client?.location);
    const [kg, setKg] = useState(userData?.client?.kg);
    const [age, setAge] = useState(userData?.client?.age);

    const handleSaveProfile = async (event) => {
        event.preventDefault();
        const client = createClient(userData.client, kg, age, location, userData.id);
        const user = {...userData, firstname, lastname, email, phone, username, client};
        console.log(user);
        await updateClient(user, setUserData);
        await handleRefresh(setUserData);
    };

    if (userData.role === "TRAINER") {
        return <TrainerProfile userData = {userData} setUserData = {setUserData} />
    }

    return (
        <div className="content profile-content">
            <form className="profile-form">
                <h2>Profile Settings</h2>
                <div className="profile-input">
                    <div className="profile-input-info">
                        <label htmlFor="firstname">Firstname</label>
                        <input 
                            id="firstname" 
                            type="text" 
                            value={firstname} 
                            onChange={(event) => setFirstname(event.target.value)}
                        />
                    </div>
                    <div className="profile-input-info">
                        <label htmlFor="lastname">Lastname</label>
                        <input 
                            id="lastname" 
                            type="text" 
                            value={lastname} 
                            onChange={(event) => setLastname(event.target.value)}
                        />
                    </div>
                </div>
                <div className="profile-input">
                    <div className="profile-input-info">
                        <label htmlFor="email">Email</label>
                        <input 
                            id="email" 
                            type="email" 
                            value={email}
                            onChange={(event) => setEmail(event.target.value)} 
                        />
                    </div>
                    <div className="profile-input-info">
                        <label htmlFor="phone">Phone Number</label>
                        <input 
                            id="phone" 
                            type="text" 
                            value={phone}
                            onChange={(event) => setPhone(event.target.value)} 
                        />
                    </div>
                </div>
                <div className="profile-input">
                    <div className="profile-input-info">
                        <label htmlFor="username">Username</label>
                        <input 
                            id="username"
                            type="username" 
                            value={username}
                            onChange={(event) => setUsername(event.target.value)} 
                        />
                    </div>
                    <div className="profile-input-info">
                        <label htmlFor="location">Location</label>
                        <input 
                            id="location"
                            type="text" 
                            value={location}
                            onChange={(event) => setLocation(event.target.value)}
                        />
                    </div>
                </div>
                <div className="profile-input">
                    <div className="profile-input-info">
                        <label htmlFor="kg">Kg</label>
                        <input 
                            id="kg" 
                            type="number" 
                            value={kg}
                            onChange={(event) => setKg(event.target.value)}
                        />
                    </div>
                    <div className="profile-input-info">
                        <label htmlFor="age">Age</label>
                        <input 
                            id="age" 
                            type="number" 
                            value={age}
                            onChange={(event) => setAge(event.target.value)} 
                        />
                    </div>
                </div>
                <button 
                    className="profile-btn"
                    type="submit"
                    onClick={(event) => handleSaveProfile(event)}  
                >Save Profile</button>
            </form>
        </div>
    );
};

export default Profile;