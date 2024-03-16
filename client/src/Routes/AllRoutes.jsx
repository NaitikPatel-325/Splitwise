import React from 'react'
import { Routes, Route } from 'react-router-dom'
import {Home} from '../Pages/Home/Home'
import {Login} from '../Pages/Login'
import {Register} from '../Pages/Register'

export const AllRoutes = () => {
  return (
    <Routes>
        <Route path = "/" element = {<Home />} />
        <Route path = "/login" element = {<Login />} />
        <Route path = "/signup" element = {< Register />} />
    </Routes>
  )
}
