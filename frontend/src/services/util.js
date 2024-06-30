export const createClient = (client, kg, age, location, user_id) => {
    return {...client, kg, age, location, user_id}
};

export const createTrainer = (trainer, startingTime, endingTime, fee, duration, location, user_id) => {
    duration = mapDurationToSeconds(duration);
    return {...trainer, startingTime, endingTime, fee, duration, location, user_id};
};

export const createSession = (date, duration, fee, status, trainer, client) => {
    return {date, duration, fee, status, trainer, client};
};

export const createDate = (date, time) => {
    let dateTime = date + "T" + time;
    return dateTime;
}

// it allows to type only time formated 
// from 00:00 to 23:59! 

export const formatTime = (value) => {

    let array = value.split("");
    
    if (array.length === 1) {
        array[0] = array[0].replace(/[^0-2]/, "");
    } else if (array.length === 2) {
        if (array[0] == 2) {
            array[1] = array[1].replace(/[^0-3]/, "");
        } else {
            array[1] = array[1].replace(/[^0-9]/, "");
        }
    } else if (array.length === 3) {
        array[2] = array[2].replace(/./, ":");
    } else if (array.length === 4) {
        array[3] = array[3].replace(/[^0-5]/, "");
    } else if (array.length === 5) {
        array[4] = array[4].replace(/[^0-9]/, "");
    } else if (array.length > 5) {
        array = array.slice(0, 5);
    }
    
    return array.join("");
};

// It allows to type only time formated text
// from 0:00 to 5:30 with a 0:30 step.

export const formatDuration = (value) => {

    let array = value.split("");
    
    if (array.length === 1) {
        array[0] = array[0].replace(/[^0-5]/, "");
    } else if (array.length === 2) {
        array[1] = array[1].replace(/./, ":");
    } else if (array.length === 3) {
        array[2] = array[2].replace(/[^03]/, "");
    } else if (array.length === 4) {
        array[3] = array[3].replace(/./, 0);
    } else if (array.length > 4) {
        array = array.slice(0, 4);
    }
    
    return array.join("");
};

const mapDurationToSeconds = (duration) => {
    let array = duration.split("");
    let hoursToSeconds = array[0] * 60 * 60;
    let minutesToSeconds = array[2] * 60;
    return hoursToSeconds + minutesToSeconds;
} ;

export const mapSecondsToDuration = (duration) => {
    duration = parseInt(duration);
    let hours = Math.floor(duration / 3600);
    let minutes = (duration % 3600) / 60;
    duration = hours + ":" + minutes + "0";
    return duration;
};

export const mapTimeToSeconds = (time) => {
    let array = time.split("");
    let hoursToSeconds = (parseInt(array[0]) * 10 + parseInt(array[1])) * 3600;
    let minutesToSeconds = (parseInt(array[3]) * 10 + parseInt(array[4])) * 60;
    return (hoursToSeconds + minutesToSeconds);
};

export const checkAvailability = (date, time, sessions, startingTime, endingTime) => {
    if (mapTimeToSeconds(time) < mapTimeToSeconds(startingTime) || mapTimeToSeconds(time) > mapTimeToSeconds(endingTime)) {
        return false;
    }

    if (!date || !time) {
        return false;
    }

    if (sessions.length === 0) {
        return true;
    }

    const dateTime = createDate(date, time);
    const found = sessions.find(session => session.date.slice(0, 16) === dateTime);
    if (found) return false;

    return true;
};

export const handleUnauthorized = () => {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
    window.location.href = "/login";
}
