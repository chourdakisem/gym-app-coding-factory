import { FaBell } from "react-icons/fa6";
import "../styles/UserNotificationBar.css"

const UserNotificationBar = ({username}) => {
    return (
        <div className="notification-bar">
            <FaBell/>
            <span>{username}</span>
        </div>
    );
};

export default UserNotificationBar;