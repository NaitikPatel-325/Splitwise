import React, { useState, useContext } from 'react';
import axios from 'axios';
import UserContext from '../../../context/create';
import { toast, ToastContainer } from 'react-toastify';

export const Group = () => {
  const { isLoggedIn,user} = useContext(UserContext);

  const [participants, setParticipants] = useState(['']);
  const [groupName, setGroupName] = useState('');
  const [currency, setCurrency] = useState('');
  const [emailErrors, setEmailErrors] = useState([]);

  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const handleParticipantChange = (index, value) => {
    const newParticipants = [...participants];
    newParticipants[index] = value;
    setParticipants(newParticipants);

    const newEmailErrors = [...emailErrors];
    newEmailErrors[index] = '';
    setEmailErrors(newEmailErrors);
  };

  const handleParticipantBlur = (index, value) => {
    const newEmailErrors = [...emailErrors];
    newEmailErrors[index] = !validateEmail(value) ? 'Invalid email address' : '';
    setEmailErrors(newEmailErrors);
  };

  const deleteParticipant = (index) => {
    const newParticipants = [...participants];
    newParticipants.splice(index, 1);
    setParticipants(newParticipants);

    const newEmailErrors = [...emailErrors];
    newEmailErrors.splice(index, 1);
    setEmailErrors(newEmailErrors);
  };

  const handleCreateGroup = async () => {
    console.log(isLoggedIn);
    if (isLoggedIn) {
      if (!groupName || !currency || participants.length === 0) {
        toast.warning('Please fill in all the fields.');
        return;
      }

      const newEmailErrors = participants.map(participant =>
        !validateEmail(participant) ? 'Invalid email address' : ''
      );

      if (newEmailErrors.some(error => error)) {
        setEmailErrors(newEmailErrors);
        toast.warning('Please enter valid email addresses for all participants.');
        return;
      } 

      try {
        await axios.post('http://localhost:8080/api/add', {
          groupName: groupName,
          currency: currency,
          participants: participants,
          useremail:user.email
        }, {withCredentials: true});
        console.log(groupName,currency,participants);
        window.location.href = '/dashboard';
      } catch (error) {
        console.error('Error creating group:', error);
      }
    } else {
      toast.error('Please login to add participants');
    }
  };

  const handleCancel = () => {
    window.location.href = '/dashboard';
  };

  return (
    <div className="flex-1 max-w-screen-md w-full mx-auto mb-2 flex flex-col gap-6 ">
      <div className='flex flex-col justify-start mt-4 bg-zinc-800 px-4 py-6'>
        <div>
          <h1 className='text-white text-2xl'>Group Information</h1>
        </div>
        <div className='mt-10'>
          <div className='flex flex-row justify-between'>
            <div className='flex flex-col w-full '>
              <p className='text-white m-2'>Group Name</p>
              <label className="input m-2 text-white input-bordered flex items-center gap-2">
                <input type="text" className="grow h-10 rounded-lg bg-zinc-950 p-4" onChange={(e) => { setGroupName(e.target.value) }} placeholder="Summer Vacations" />
              </label>
              <p className='text-white m-2'>Enter A Name For A Group</p>
            </div>

            <div className='flex flex-col w-full '>
              <p className='text-white m-2'>Currency Symbol</p>
              <label className="input input-bordered text-white flex items-center gap-2">
                <input type="text" onChange={(e) => { setCurrency(e.target.value) }} className="m-2 grow h-10 rounded-lg bg-zinc-950 w-full p-4" placeholder="$ INR" />
              </label>
              <p className='m-2 text-white'>We Will Use It To Display It</p>
            </div>
          </div>
        </div>
      </div>

      <div className='flex flex-col justify-start mt-4 bg-zinc-800 px-4 py-6'>
        <div>
          <h1 className='text-white text-2xl'>Participants</h1>
        </div>
        <div className='mt-10'>
          {participants.map((participant, index) => (
            <div key={index} className="flex items-center text-white">
              <input
                type="email"
                value={participant}
                onChange={(e) => handleParticipantChange(index, e.target.value)}
                onBlur={(e) => handleParticipantBlur(index, e.target.value)}
                placeholder="Participant Email"
                className="m-2 grow h-10 rounded-lg bg-zinc-950 p-4"
              />
              {emailErrors[index] && <span className="text-red-500">{emailErrors[index]}</span>}
              <button onClick={() => deleteParticipant(index)} className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
                Delete
              </button>
            </div>
          ))}
          <button onClick={() => setParticipants([...participants, ''])} className="m-2 bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
            Add Participant
          </button>
        </div>
        <div>
          <button onClick={handleCreateGroup} className="m-2 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Create
          </button>
          <button onClick={handleCancel} className="m-2 bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded">
            Cancel
          </button>
        </div>
      </div>
      <ToastContainer position='top-center' />
    </div>
  );
};
