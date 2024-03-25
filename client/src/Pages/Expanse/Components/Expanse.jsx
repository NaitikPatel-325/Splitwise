import React, { useState } from 'react';

export const Expanse = (id) => {
  const [expanseName, setExpanseName] = useState('');
  const [totalExpenses, setTotalExpenses] = useState('');
  
  
  return (
    <div className="text-white">
      <div className="max-w-lg mx-auto px-4 py-6 bg-zinc-800 rounded-lg shadow-md">
        <h1 className="text-2xl font-semibold mb-4">Expenses Information</h1>
        <div className="flex flex-col space-y-4">
          <div>
            <label className="block text-sm font-medium">Expense Name</label>
            <input
              type="text"
              className="input-text w-full bg-black"
              value={expanseName}
              onChange={(e) => setExpanseName(e.target.value)}
              placeholder="Enter an Expense Name"
            />
          </div>
          <div>
            <label className="block text-sm font-medium">Amount</label>
            <input
              type="text"
              className="input-text w-full bg-black"
              value={totalExpenses}
              onChange={(e) => setTotalExpenses(e.target.value)}
              placeholder="Enter the Amount"
            />
          </div>
        </div>
      </div>
    </div>
  );
};
