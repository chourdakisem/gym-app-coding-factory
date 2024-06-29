import { 
    update,
    fetchTrainersByLastname, 
    fetchTrainerById, 
    createSession, 
    fetchSessionByTrainerId, 
    fetchSessionByClientId,
    fetchSessionDetailsByClientId,
    fetchSessionDetailsByTrainerId,
    deleteSessionById,
    updateSessionById,
    fetchSessionById
} from "../api/api"
import { handleUnauthorized } from "./util";


export const updateClient = async (user, setUserData) => {
    const response = await update(user);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const userData = await response.json();
    if (!response.ok) {
        console.log(userData);
        return;
    }
    setUserData(userData);
};

export const updateTrainer = async (user, setUserData) => {
    const response = await update(user);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const userData = await response.json();
    if (!response.ok) {
        console.log(userData);
        return;
    }
    setUserData(userData);
};

export const getTrainersByLastname = async (lastname, setTrainers, setError) => {
    const response = await fetchTrainersByLastname(lastname);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const trainers = await response.json();
    if (!response.ok) {
        setError(trainers.message);
        return false;
    }
    setTrainers(trainers);
    return true;
};

export const getTrainerById = async (id, setTrainer, setError) => {
    const response = await fetchTrainerById(id);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const trainer = await response.json();
    if (!response.ok) {
        setError(trainer.message);
        return;
    }
    setTrainer(trainer);
};

export const createNewSession = async (session) => {
    const response = await createSession(session);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const newSession = await response.json();
    if (!response.ok) {
        console.log(newSession.message);
    }
    console.log("Session created");
    console.log(newSession);
}

export const getSessionByTrainerId = async (id) => {
    const response = await fetchSessionByTrainerId(id);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const sessions = await response.json();
    if (!response.ok) {
        return;
    }
    return sessions;
};

export const getSessionByClientId = async (id) => {
    const response = await fetchSessionByClientId(id);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const sessions = await response.json();
    if (!response.ok) {
        return;
    }
    return sessions;
};

export const getSessionDetailsByClientId = async (id, setSessionDetails) => {
    const response = await fetchSessionDetailsByClientId(id);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const sessionDetails = await response.json();
    if (!response.ok) {
        return;
    }
    setSessionDetails(sessionDetails);
};

export const getSessionDetailsByTrainerId = async (id, setSessionDetails) => {
    const response = await fetchSessionDetailsByTrainerId(id);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const sessionDetails = await response.json();
    if (!response.ok) {
        return;
    }
    setSessionDetails(sessionDetails);
};

export const deleteSession = async (id) => {
    const response = await deleteSessionById(id);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const deletedSession = await response.json();
    if (!response.ok) {
        return;
    }
};

export const updateSession = async (id, session) => {
    const response = await updateSessionById(id, session);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const updatedSession = await response.json();
    if (!response.ok) {
        return;
    }
};

export const getSessionById = async (id) => {
    const response = await fetchSessionById(id);

    if (response.status === 403) {
        handleUnauthorized();
    }

    const updatedSession = await response.json();
    if (!response.ok) {
        return;
    }
    return updatedSession;
};
