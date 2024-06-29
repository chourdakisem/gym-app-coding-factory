import { useState, useEffect } from "react";
import "../styles/Schedule.css";
import "../styles/TrainerSchedule.css"
import { deleteSession, getSessionById, getSessionDetailsByTrainerId, updateSession } from "../services/dataService";
import { mapSecondsToDuration } from "../services/util";
import { handleRefresh } from "../services/auth";

const TrainerSchedule = ({userData, setUserData, refresh, setRefresh}) => {

    const [sessionDetails, setSessionDetails] = useState([]);

    useEffect(() => {
        (async () => {
            await getSessionDetailsByTrainerId(userData.trainer?.id, setSessionDetails);
            await handleRefresh(setUserData);
        })();
    }, [refresh]);

    const handleRejectButton = async (event, id) => {
        event.preventDefault();
        await deleteSession(id);
        await handleRefresh(setUserData);
        setRefresh(!refresh);
    };

    const handleAcceptButton = async (event, id) => {
        event.preventDefault();
        let session = await getSessionById(id);
        session = {...session, status: "ACCEPTED"}
        await updateSession(id, session);
        await handleRefresh(setUserData);
        setRefresh(!refresh)
    };

    return (
        <div className="content schedule-content">
            <h2>Training Schedule</h2>
            <ul className="schedule-list">
                <li className="schedule-list-item trainer-schedule">
                    <span>Client</span>
                    <span>Date & Time</span>
                    <span>Duration</span>
                    <span>Fee</span>
                    <span>Status</span>
                    <span>Actions</span>
                </li>
                {sessionDetails.map(sessionData => {
                    return (
                        <li key={sessionData.id} className="schedule-list-item trainer-schedule">
                            <span>{sessionData.firstname} {sessionData.lastname}</span>
                            <span>{sessionData.date.slice(0, 16)}</span>
                            <span>{mapSecondsToDuration(sessionData.duration)}</span>
                            <span>{sessionData.fee}$</span>
                            <span>{sessionData.status}</span>
                            <span className="trainer-schedule-buttons">
                                <button 
                                    className="accept-btn"
                                    onClick={(event) => handleAcceptButton(event, sessionData.id)}
                                    style={{display: sessionData.status === "ACCEPTED" ? "none" : "inline-block"}}
                                >Accept</button>
                                <button 
                                    className="reject-btn"
                                    onClick={(event) => handleRejectButton(event, sessionData.id)}
                                    style={{display: sessionData.status === "ACCEPTED" ? "none" : "inline-block"}}
                                >Reject</button>
                            </span>
                        </li>
                    );
                })}
            </ul>
        </div>
    );
};

export default TrainerSchedule;