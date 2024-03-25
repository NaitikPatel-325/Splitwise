import React from 'react'
import { Routes, Route } from 'react-router-dom'
import {Home} from '../Pages/Home/Home'
import {Login} from '../Pages/Login'
import {Register} from '../Pages/Register'
import {Profile} from '../Pages/Profile/Profile'

export const AllRoutes = () => {
  return (
    <Routes>
        <Route path = "/Home" element = {<Home />} />
        <Route path = "/login" element = {<Login />} />
        <Route path = "/signup" element = {< Register />} />
        <Route path = "/profile" element = {<Profile />} />
    </Routes>
  )
}
