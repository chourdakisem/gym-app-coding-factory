import { useState } from "react";
import "../styles/Profile.css"
import { createTrainer, formatDuration, formatTime, mapSecondsToDuration } from "../services/util";
import { updateTrainer } from "../services/dataService";
import { handleRefresh } from "../services/auth";


const TrainerProfile = ({userData, setUserData}) => {

    const [firstname, setFirstname] = useState(userData.firstname);
    const [lastname, setLastname] = useState(userData.lastname);
    const [email, setEmail] = useState(userData.email);
    const [phone, setPhone] = useState(userData.phone);
    const [username, setUsername] = useState(userData.username);
    const [location, setLocation] = useState(userData.trainer?.location);
    const [startingTime, setStartingTime] = userData.trainer?.startingTime ? useState(userData.trainer.startingTime.slice(0, 5)) : useState("");
    const [endingTime, setEndingTime] = userData.trainer?.endingTime ? useState(userData.trainer.endingTime.slice(0, 5)) : useState("");
    const [fee, setFee] = useState(userData.trainer?.fee);
    const [duration, setDuration] = userData.trainer?.duration ? useState(mapSecondsToDuration(userData.trainer.duration)) : useState("");

    const handleTimeChange = (event, setTime) => {
        const inputTime = event.target.value;
        const formatedTime = formatTime(inputTime);
        setTime(formatedTime);
    };

    const handleDurationChange = (event, setDuration) => {
        const inputDuration = event.target.value;
        const formatedDuration = formatDuration(inputDuration);
        setDuration(formatedDuration);
    };

    const handleSaveProfile = async (event) => {
        event.preventDefault();
        const trainer = createTrainer(userData.trainer, startingTime, endingTime, fee, duration, location);
        const user = {...userData, firstname, lastname, username, email, phone, trainer}
        console.log(user);
        await updateTrainer(user, setUserData);
        await handleRefresh(setUserData);
    };

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
                            type="text" 
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
                            type="text" 
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
                        <label htmlFor="onboarding">Starting time</label>
                        <input 
                            id="onboarding" 
                            type="text" 
                            value={startingTime}
                            onChange={(event) => handleTimeChange(event, setStartingTime)}
                        />
                    </div>
                    <div className="profile-input-info">
                        <label htmlFor="offboarding">Ending time</label>
                        <input 
                            id="offboarding" 
                            text="text"
                            value={endingTime}
                            onChange={(event) => handleTimeChange(event, setEndingTime)}
                        />
                    </div>
                </div><div className="profile-input">
                    <div className="profile-input-info">
                        <label htmlFor="fee">Fee($)</label>
                        <input 
                            id="fee" 
                            type="number" 
                            value={fee}
                            onChange={(event) => setFee(event.target.value)} 
                        />
                    </div>
                    <div className="profile-input-info">
                        <label htmlFor="duration">Duration</label>
                        <input 
                            id="duration" 
                            type="text"
                            value={duration} 
                            onChange={(event) => handleDurationChange(event, setDuration)}
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

export default TrainerProfile;