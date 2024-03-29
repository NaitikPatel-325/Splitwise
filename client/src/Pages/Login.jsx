import React,{useContext} from 'react';
import axios from 'axios';
import  UserContext  from '../context/create';


export const Login = () => {
  const [name, setname] = React.useState("");
  const [password, setPassword] = React.useState("");

  const {
    setIsLoggedIn,
    Login
  } = useContext(UserContext);
  
  const handleLogin = (e) => {
    e.preventDefault(); 
    axios.post('http://localhost:8080/user/signin', {
      username: name,
      password: password,
    })
    .then(response => {
      console.log(response.data);
      console.log(response.headers);
      setIsLoggedIn(true);
      sessionStorage.setItem("jwt", response.data);
      Login(response.data.jwtToken, response.data.username);
      console.log(response.data);
      window.location.href = "/home";
    })
    .catch(error => {
      console.error("Error logging in:", error);
    });
  }

  return (
    <div>
      <a href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet" />
      <div className="bg-no-repeat bg-cover bg-center relative" style={{backgroundImage: 'url(https://images.unsplash.com/photo-1579621970563-ebec7560ff3e?ixlib=rb-1.2.1&amp;ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&amp;auto=format&amp;fit=crop&amp;w=1951&amp;q=80)'}}>
        <div className="absolute bg-gradient-to-b from-black-500 to-black-400 opacity-75 inset-0 z-0"></div>
        <div className="min-h-screen sm:flex sm:flex-row mx-0 justify-center">
          <div className="flex-col flex  self-center p-10 sm:max-w-5xl xl:max-w-2xl  z-10">
            <div className="self-start hidden lg:flex flex-col  text-white">
              <img src="" className="mb-3" alt="" />
              <h1 className="mb-3 font-bold text-5xl">Hi ? Welcome Back  </h1>
              <p className="pr-3">Lorem ipsum is placeholder text commonly used in the graphic, print,
                and publishing industries for previewing layouts and visual mockups</p>
            </div>
          </div>
          <div className="flex justify-center self-center  z-10">
            <div className="p-12 bg-white mx-auto rounded-2xl w-100 ">
              <div className="mb-4">
                <h3 className="font-semibold text-2xl text-gray-800">Sign In </h3>
                <p className="text-gray-500">Please sign in to your account.</p>
              </div>
              <div className="space-y-5">
                <div className="space-y-2">
                  <label className="text-sm font-medium text-gray-700 tracking-wide">Username</label>
                  <input
                      className="w-full text-base px-4 py-2 border  border-gray-300 rounded-lg focus:outline-none focus:border-green-400"
                      type="text" onChange={(e)=>setname(e.target.value)} placeholder="Enter Your Username"/>
                </div>
                <div className="space-y-2">
                  <label className="mb-5 text-sm font-medium text-gray-700 tracking-wide">
                    Password
                  </label>
                  <input
                      className="w-full content-center text-base px-4 py-2 border  border-gray-300 rounded-lg focus:outline-none focus:border-green-400"
                      type="password" placeholder="Enter your password" onChange={(e)=>setPassword(e.target.value)} />
                </div>
                <div className="flex items-center justify-between">
                  <div className="flex items-center">
                    <input id="remember_me" name="remember_me" type="checkbox"
                           className="h-4 w-4 bg-blue-500 focus:ring-blue-400 border-gray-300 rounded"/>
                    <label htmlFor="remember_me" className="ml-2 block text-sm text-gray-800">
                      Remember me
                    </label>
                  </div>
                  <div className="text-sm">
                    <a href="#" className="text-green-400 hover:text-green-500">
                      Forgot your password?
                    </a>
                  </div>
                </div>
                <div>
                  <button type="submit" onClick={handleLogin}
                          className="w-full flex justify-center bg-green-400  hover:bg-green-500 text-gray-100 p-3  rounded-full tracking-wide font-semibold  shadow-lg cursor-pointer transition ease-in duration-500">
                    Sign in
                  </button>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
