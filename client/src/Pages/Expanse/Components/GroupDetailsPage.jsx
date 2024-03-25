import axios from 'axios'
import React from 'react'



export const GroupDetailsPage = () => {

  axios.get('http://localhost:8080/group/member').then(response => {
    console.log('Group details:', response);
  })
  .then (response => {
    console.log('Group details:', response);
  })
  .catch(error => {
    console.error('Error:', error);
  });

  return (
    <div className="text-white">
      <div className="max-w-lg mx-auto px-4 py-6 bg-zinc-800 rounded-lg shadow-md">
        <h1 className="text-2xl font-semibold mb-4">Paid For</h1>
        
      </div>
    </div>
  )
}
