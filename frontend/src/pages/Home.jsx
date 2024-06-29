import Search from "../components/Search";

const Home = ({setTrainers, setUserData}) => {
    return (
        <Search setTrainers = {setTrainers} setUserData = {setUserData} />
    );
};

export default Home;