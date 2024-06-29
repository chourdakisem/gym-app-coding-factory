import { useNavigate } from "react-router-dom";
import { useState } from "react";
import "../styles/Search.css";
import { FaMagnifyingGlass } from "react-icons/fa6";
import { getTrainersByLastname } from "../services/dataService";
import { handleRefresh } from "../services/auth";

const Search = ({setTrainers, setUserData}) => {

    const [lastname, setLastname] = useState();
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleClick = async (event) => {
        event.preventDefault();
        setTrainers([]);
        const success  = await getTrainersByLastname(lastname, setTrainers, setError);
        await handleRefresh(setUserData);
        if (success) navigate("/trainers");
    };

    return (
        <div className="search-content">
            <h2>Find the Best Trainers Near You</h2>
            <div className="search-box">
                <input 
                    className="search-bar" 
                    type="text"  
                    value={lastname}
                    onChange={(event) => setLastname(event.target.value)}    
                />
                <button 
                    className="fa-magnifying-glass"
                    type="submit"
                    onClick={(event) => handleClick(event)}
                ><FaMagnifyingGlass /></button>
            </div>
            <h2 style={{color: "red"}}>{error}</h2>
        </div>
    );
};

export default Search;