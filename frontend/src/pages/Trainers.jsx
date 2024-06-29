import Trainer from "../components/Trainer";
import "../styles/Trainers.css"

const Trainers = ({trainers}) => {

    return (
        <div className="content trainers-content">
            {trainers.map(trainer => <Trainer key={trainer.id} trainer = {trainer} />)}
        </div>
    );
};

export default Trainers;