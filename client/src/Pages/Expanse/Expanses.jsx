import React from 'react';
import { GroupDetailsPage } from './Components/GroupDetailsPage';
import { Expanse } from './Components/Expanse';

export const Expanses = ({ match }) => {
  const id = match.params.id;
  return (
    <div className="flex-1 max-w-screen-md w-full mx-auto mb-2 flex flex-col gap-6">
      <Expanse id={id} />
      <GroupDetailsPage id={id} />
      
    </div>
  );
};
