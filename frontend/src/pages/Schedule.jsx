import { useState, useEffect } from "react";
import "../styles/Schedule.css";
import TrainerSchedule from "../components/TrainerSchedule";
import { getSessionDetailsByClientId } from "../services/dataService";
import { mapSecondsToDuration } from "../services/util";
import { handleRefresh } from "../services/auth";

const Schedule = ({userData, setUserData}) => {

    const [sessionDetails, setSessionDetails] = useState([]);
    const [refresh, setRefresh] = useState(false);

    useEffect(() => {
        (async () => {
            await getSessionDetailsByClientId(userData.client?.id, setSessionDetails);
            await handleRefresh(setUserData);
        })(); 
    }, [refresh]);

    if (userData?.role === "TRAINER") {
        return <TrainerSchedule userData = {userData} setUserData = {setUserData} refresh = {refresh} setRefresh = {setRefresh} />;
    }


    return (
        <div className="content schedule-content">
            <h2>Training Schedule</h2>
            <ul className="schedule-list">
                <li className="schedule-list-item">
                    <span>Trainer</span>
                    <span>Date & Time</span>
                    <span>Duration</span>
                    <span>Fee</span>
                    <span>Status</span>
                </li>
                {sessionDetails.map(sessionData => {
                    return (
                        <li key={sessionData.id} className="schedule-list-item">
                            <span>{sessionData.firstname} {sessionData.lastname}</span>
                            <span>{sessionData.date.slice(0, 16)}</span>
                            <span>{mapSecondsToDuration(sessionData.duration)}</span>
                            <span>{sessionData.fee}$</span>
                            <span>{sessionData.status}</span>
                        </li>
                    )
                })}
            </ul>
        </div>
    );
};

export default Schedule;