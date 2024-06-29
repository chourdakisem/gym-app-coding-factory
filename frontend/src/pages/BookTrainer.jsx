import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import "../styles/BookTrainer.css";
import { createNewSession, getSessionByTrainerId, getTrainerById } from "../services/dataService";
import { checkAvailability, createDate, createSession, formatTime } from "../services/util";
import { handleRefresh } from "../services/auth";

const BookTrainer = ({userData, setUserData}) => {

    const { trainerId } = useParams();
    const [trainer, setTrainer] = useState("");
    const [error, setError] = useState("");
    const [sessionTime, setSessionTime] = useState("");
    const [sessionDate, setSessionDate] = useState("");
    const [isLoaded, setIsLoaded] = useState(false);
    const [isChecked, setIsChecked] = useState(false);

    useEffect(() => {
        (async () => await getTrainerById(trainerId, setTrainer, setError))();
        setIsLoaded(true);
    }, [trainerId]);

    const handleTimeChange = (event, setTime) => {
        const inputTime = event.target.value;
        const formatedTime = formatTime(inputTime);
        setTime(formatedTime);
    };

    const handleBooking = async (event) => {
        event.preventDefault();
        const dateTime = createDate(sessionDate, sessionTime);
        const session = createSession(dateTime, trainer.duration, trainer.fee, "PENDING", trainer, userData.client);
        await createNewSession(session);
        await handleRefresh(setUserData);
        setIsChecked(false);
    };

    const handleAvailability = async (event) => {
        event.preventDefault();
        const sessions = await getSessionByTrainerId(trainerId);
        if (sessions?.length === 0) {
            setIsChecked(true);
            return;
        }
       setIsChecked(checkAvailability(sessionDate, sessionTime, sessions, trainer.startingTime, trainer.endingTime));
    }

    if (!isLoaded) return <h2>Loading...</h2>

    if (error) return <h2 style={{color: "red"}}>{error}</h2>

    return (
        <div className="content booking-content">
            <h2>{trainer.user?.firstname} {trainer.user?.lastname}</h2>
            <span>Fee: {trainer.fee}$</span>
            <span>Timings: {trainer.startingTime?.slice(0, 5)} - {trainer.endingTime?.slice(0, 5)}</span>
            <form className="booking-form">
                <input
                    type="date" 
                    placeholder="Select Date"
                    value={sessionDate}
                    onChange={(event) => setSessionDate(event.target.value)}
                />
                <input 
                    type="text" 
                    placeholder="Select Time"
                    value={sessionTime}
                    onChange={(event) => handleTimeChange(event, setSessionTime)}
                />
                <button
                    className="booking-btn"
                    onClick={(event) => handleAvailability(event)}
                >Check Availability</button>
                <button
                    className="booking-btn"
                    type="submit"
                    disabled={!isChecked}
                    onClick={(event) => handleBooking(event)}
                    style={{cursor: isChecked ? "pointer" : "not-allowed"}}
                >Book Training</button>
            </form>
        </div>
    );
};

export default BookTrainer;