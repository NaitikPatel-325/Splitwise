import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import UserContext from '../../../context/create';

export const AllGroups = () => {
  const { isLoggedIn, user } = useContext(UserContext);
  const navigate = useNavigate();

  const [groups, setGroups] = useState([]);

  useEffect(() => {
    if (isLoggedIn) {
      fetchGroups();
    } else {
      toast.error('Please login to view groups');
    }
  }, [isLoggedIn]);

  const fetchGroups = () => {
    const userEmail = user.email;

    axios.get(`http://localhost:8080/api/get?email=${encodeURIComponent(userEmail)}`, {
      withCredentials: true,
    })
    .then(response => {
      setGroups(response.data);
      console.log('Groups:', response.data);
    })
    .catch(error => {
      console.error('Error fetching groups:', error);
    });
  };

  const handleDetailsClick = (groupId) => {
    navigate(`/group/${groupId}`, { groupId }); 
  };

  const handleDeleteGroup = (groupId) => {
    axios.delete(`http://localhost:8080/api/delete/${groupId}`, {
      withCredentials: true,
    })
    .then(() => {
      setGroups(groups.filter(group => group.id !== groupId));
      toast.success('Group deleted successfully');
    })
    .catch(error => {
      console.error('Error deleting group:', error);
      toast.error('Error deleting group');
    });
    console.log(`Deleting group with ID ${groupId}`);
  };

  return (
    <div className="flex-1 max-w-screen-md w-full mx-auto mb-2">
      <div className="overflow-x-auto">
        <table className="table-auto w-full border-collapse">
          <thead>
            <tr className="bg-gray-800 text-white">
              <th className="py-2 px-4">No</th>
              <th className="py-2 px-4">Group Name</th>
              <th className="py-2 px-4">Currency</th>
              <th className="py-2 px-4">Details</th>
              <th className="py-2 px-4">Actions</th>  
            </tr>
          </thead>
          <tbody>
            {groups.map((group, index) => (
              <tr key={group.id} className={index % 2 === 0 ? 'bg-gray-200' : 'bg-white'}>
                <td className="py-2 px-4">{index + 1}</td>
                <td className="py-2 px-4">{group.groupName}</td>
                <td className="py-2 px-4">{group.currency}</td>
                <td className="py-2 px-4">
                  <button 
                    onClick={() => handleDetailsClick(group.id)} 
                    className="text-white bg-blue-500 py-1 px-3 rounded-lg hover:bg-blue-600 focus:outline-none focus:bg-blue-600"
                  >
                    Details
                  </button>
                </td>
                <td className="py-2 px-4">
                  <button 
                    onClick={() => handleDeleteGroup(group.id)} 
                    className="text-white bg-red-500 py-1 px-3 rounded-lg hover:bg-red-600 focus:outline-none focus:bg-red-600"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <ToastContainer position='top-center'/>
    </div>
  );
};
