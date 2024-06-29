import { useNavigate } from "react-router-dom";

import { mapSecondsToDuration } from "../services/util";
import "../styles/Trainer.css";

const Trainer = ({trainer}) => {

    const navigate = useNavigate();

    return (
        <div className="trainer-card" onClick={() => navigate(`/trainers/${trainer.id}`)}>
            <div className="trainer-info">
                <h3>{trainer.user.firstname} {trainer.user.lastname}</h3>
                <span>Calisthenics</span>
                <div className="session-info">
                    <span>Fee: {trainer.fee}$</span>
                    <span>Duration: {mapSecondsToDuration(trainer.duration)}</span>
                </div>
                <span>{trainer.location}, Greece</span>
            </div>
            <div className="img-container">
                <img src="/img/user.jpg" alt="" />
            </div>
        </div>
    );
};

export default Trainer;