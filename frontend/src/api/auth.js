export const login = async (username, password) => {
    const url = "http://localhost:8080/login";
    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({username, password})
    });
    return response;
};

export const register = async (user) => {
    const url = "http://localhost:8080/register";
    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    });
    return response;
};

export const refresh = async () => {
    const token = localStorage.getItem("refreshToken")
    const url = "http://localhost:8080/refresh";
    const response = await fetch(url, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    return response;
}