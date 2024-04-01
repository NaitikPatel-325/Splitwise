import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom'; 
import { toast ,ToastContainer} from 'react-toastify'; 
import UserContext from '../../context/create';

export const Home = () => {
  const { isLoggedIn } = useContext(UserContext);
  const navigate = useNavigate();
  
  const handleCreateGroup = () => {
    if(isLoggedIn) {
      navigate('/dashboard');
    } else {  
      toast.error("You should be logged in to create a group.");
    }
  };

  return (
    <div className="flex flex-col justify-center items-center h-full mt-64">
      <h1 className="text-center text-6xl text-gray-600">
        <span className='text-white'>Share</span> <span className='text-green-500'>Expenses</span><br />
        <span className='text-white'>with</span> <span className='text-green-500'>Friends &</span><br />
        <span className='text-white text-center'> Family</span>
      </h1>
      <button 
        className="bg-green-500 hover:bg-green-700 text-white  py-4 px-8 rounded mt-4"
        onClick={handleCreateGroup} 
      >
        Create Group
      </button>
      <ToastContainer position="top-center" />
    </div>
  );
};
