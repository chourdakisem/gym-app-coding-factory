import { Outlet } from "react-router-dom";
import UserNotificationBar from "./UserNotificationBar";

const ContentLayout = ({username}) => {
    return (
        <>
            <UserNotificationBar username = {username} />
            <Outlet />
        </>
    );
};

export default ContentLayout;