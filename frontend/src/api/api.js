export const update = (user) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/users/${user.id}`;
    const response = fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(user)
    });
    return response;
};

export const fetchSessionByTrainerId = (id) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/trainers/${id}/sessions`;
    const response = fetch(url, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    return response;
};

export const fetchSessionByClientId = (id) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/clients/${id}/sessions`;
    const response = fetch(url, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    return response;
};

export const fetchTrainerById = (id) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/trainers/${id}`;
    const response = fetch(url, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    return response;
};

export const fetchTrainersByLastname = (lastname) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/trainers/trainer/${lastname}`;
    const response = fetch(url, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    return response;
};

export const createSession = (session) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/sessions`;
    const response = fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(session)
    });
    return response;
};

export const fetchSessionDetailsByClientId = (id) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/clients/${id}/session-details`;
    const response = fetch(url, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    return response;
}

export const fetchSessionDetailsByTrainerId = (id) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/trainers/${id}/session-details`;
    const response = fetch(url, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    return response;
};

export const deleteSessionById = (id) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/sessions/${id}`;
    const response = fetch(url, {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${token}` 
        }
    });
    return response;
}

export const updateSessionById = (id, session) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/sessions/${id}`;
    const response = fetch(url, {
        method: "PUT",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json" 
        },
        body: JSON.stringify(session)
    });
    return response;
};

export const fetchSessionById = (id) => {
    const token = localStorage.getItem("accessToken");
    const url = `http://localhost:8080/sessions/${id}`;
    const response = fetch(url, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}` 
        }
    });
    return response;
}
