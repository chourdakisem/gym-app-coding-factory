import { useState } from 'react'
import {BrowserRouter, Routes, Route, Link} from 'react-router-dom';
import './App.css'
import Layout from './components/Layout';
import Login from './pages/Login';
import Register from './pages/Register';
import Home from './pages/Home';
import ContentLayout from './components/ContentLayout';
import Profile from './pages/Profile';
import Schedule from './pages/Schedule';
import Trainers from './pages/Trainers';
import BookTrainer from './pages/BookTrainer';
import ProtectedRoute from './components/ProtecedRoute';


function App() {

  const [userData, setUserData] = useState(null);
  const [trainers, setTrainers] = useState([]);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout userData = {userData} setUserData = {setUserData}/>}>
          <Route path="/" element={<ContentLayout username = {userData?.username} />}>
            <Route index element={<ProtectedRoute user = {userData}><Home trainers = {trainers} setTrainers = {setTrainers} setUserData = {setUserData} /></ProtectedRoute>} />
            <Route path="profile" element={<Profile userData = {userData} setUserData = {setUserData} />} />
            <Route path="schedule" element={<Schedule userData = {userData} setUserData = {setUserData} />} />
            <Route path="trainers" element={<Trainers trainers={trainers} />} />
            <Route path="trainers/:trainerId" element={<BookTrainer userData = {userData} setUserData = {setUserData} />} />
          </Route>
        </Route>
        <Route path="/login" element={<Login userData = {userData} setUserData = {setUserData} />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
