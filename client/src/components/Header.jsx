import React, { useState ,useContext} from 'react';
import  UserContext  from '../context/create';

export const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const {
    isLoggedIn
  } = useContext(UserContext);
  

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  return (
    <header className="dark:bg-gray-800 dark:text-gray-100">
      <div className="container flex justify-between items-center h-16 mx-auto">
        <a rel="noopener noreferrer" href="#" aria-label="Back to homepage" className="flex items-center p-2">
          {/* logo */}
        </a>
        <button onClick={toggleMenu} className="p-4 lg:hidden">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" className="w-6 h-6 dark:text-gray-100">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16"></path>
          </svg>
        </button>
        <div className="lg:flex hidden">
          <ul className="flex items-center space-x-4">
            <li><a rel="noopener noreferrer" href="/Home" className="flex items-center px-4 -mb-1 border-b-2 dark:border-transparent">Home</a></li>
            <li><a rel="noopener noreferrer" href="/dashboard" className="flex items-center px-4 -mb-1 border-b-2 dark:border-transparent">Dashboard</a></li>
            {isLoggedIn && <li><a rel="noopener noreferrer" href="/profile" className="flex items-center px-4 -mb-1 border-b-2 dark:border-transparent">Profile</a></li>}
          </ul>
          {!isLoggedIn &&<div className="items-center flex-shrink-0 hidden lg:flex">
             <a href="/login"><button className="self-center px-8 py-3 rounded">Sign in</button></a>
            <a href="/signup"><button className="self-center px-8 py-3 font-semibold rounded dark:bg-violet-400 dark:text-gray-900">Sign up</button></a>
          </div>
          }
        </div>
      </div>
      <div className={`lg:hidden ${isMenuOpen ? 'block' : 'hidden'} absolute top-16 left-0 right-0 bg-white dark:bg-gray-800 shadow-md`}>
  <ul className="flex flex-col justify-between py-4">
    <li><a rel="noopener noreferrer" href="/Home" className="block px-4 py-2 text-gray-900 dark:text-gray-100 hover:text-gray-600">Home</a></li>
    <li><a rel="noopener noreferrer" href="/dashboard" className="block px-4 py-2 text-gray-900 dark:text-gray-100 hover:text-gray-600">dashboard</a></li>
    {isLoggedIn && <li><a rel="noopener noreferrer" href="/profile" className="block px-4 py-2 text-gray-900 dark:text-gray-100 hover:text-gray-600">Profile</a></li>}
    {!isLoggedIn && <li><a rel="noopener noreferrer" href="/login" className="block px-4 py-3 rounded text-white">Sign in</a></li>}
    {!isLoggedIn && <li><a rel="noopener noreferrer" href="/signup" className="block px-4 py-3 font-semibold rounded w dark:bg-violet-400 dark:text-gray-900">Sign up</a></li>}
  </ul>
</div>
    </header>
  );
};
