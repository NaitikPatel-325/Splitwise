import React, { useState } from 'react';
import { Group } from './Component/Group'; 
import { AllGroups } from './Component/AllGroups';
import { History } from './Component/History';

export const Dashboard = () => {
  const [activeTab, setActiveTab] = useState(1);

  const handleTabClick = (tabIndex) => {
    setActiveTab(tabIndex);
  };

  return (
    <div className='flex flex-col justify-between bg-zinc-950'>
    <div role="tablist" className="flex space-x-4 justify-center">
      <a
        role="tab"
        className={`px-4 py-2 rounded-lg ${
          activeTab === 1 ? 'bg-blue-500 text-white' : 'bg-gray-200 hover:bg-gray-300'
        } transition duration-300`}
        onClick={() => handleTabClick(1)}
      >
        Group
      </a>
     

      <a
        role="tab"
        className={`px-4 py-2 rounded-lg ${
          activeTab === 2 ? 'bg-blue-500 text-white' : 'bg-gray-200 hover:bg-gray-300'
        } transition duration-300`}
        onClick={() => handleTabClick(2)}
      >
        AllGroups
      </a>
      <a
        role="tab"
        className={`px-4 py-2 rounded-lg ${
          activeTab === 3 ? 'bg-blue-500 text-white' : 'bg-gray-200 hover:bg-gray-300'
        } transition duration-300`}
        onClick={() => handleTabClick(3)}
      >
        History
      </a>
     
      </div>
      <div className="tab-content mt-4"> 
        {activeTab === 1 && <Group />}
        {activeTab === 2 && <AllGroups />}
        {activeTab === 3 && <History />}
      </div>
    </div>
  );
};
