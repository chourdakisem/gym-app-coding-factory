import { login, refresh, register } from "../api/auth"

export const handleLogin = async (event, username, password, setUserData, navigate, setError) => {
    setError(null);
    event.preventDefault();
    const response = await login(username, password);
    const userData = await response.json();
    if (!response.ok) {
        setError({message: "Bad credentials"});
        return;
    }
    
    const { accessToken, refreshToken, user } = userData;
    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);
    console.log(user);
    setUserData(user);
    navigate("/");
};

export const handleLogout = (setUserData) => {
    setUserData(null);
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
};

export const handleRegister = async (user, navigate, setError) => {
    const response = await register(user);
    const userData = await response.json();
    if (response.status === 409) {
        setError(userData.message);
        return;
    }

    if (!response.ok) {
        setError(userData[0]?.message);
        return;
    } 
    navigate("/login");
};

export const handleRefresh = async (setUserData) => {
    const response = await refresh();
    const {user, accessToken, refreshToken} = await response.json();
    if (!response.ok) {
        return;
    }
    
    localStorage.setItem("refreshToken", refreshToken);
    localStorage.setItem("accessToken", accessToken);
    setUserData(user);
};