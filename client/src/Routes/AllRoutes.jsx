import React from 'react'
import { Routes, Route } from 'react-router-dom'
import {Home} from '../Pages/Home/Home'
import {Login} from '../Pages/Login'
import {Register} from '../Pages/Register'
import {Profile} from '../Pages/Profile/Profile'
import {Dashboard} from '../Pages/Dashboard/Dashboard'
import {Expanses} from '../Pages/Expanse/Expanses'

export const AllRoutes = () => {
  return (
    <Routes>
        <Route path = "/Home" element = {<Home />} />
        <Route path = "/login" element = {<Login />} />
        <Route path = "/signup" element = {< Register />} />
        <Route path = "/profile" element = {<Profile />} />
        <Route path="/dashboard" element = {<Dashboard />} />
        <Route path="/group/:id" element={<Expanses/>} />
    </Routes>
  )
}
