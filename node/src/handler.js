'use strict';

const trainings = require('./trainings.js')

module.exports.create = async (event, context) => {
  try {
    await trainings.create(JSON.parse(event.body));
  } catch(err) {
    return {
      statusCode: 500,
      body: JSON.stringify({
        message: 'Failed to create training',
        input: err,
      }),
    };
  }

  return {
    statusCode: 200,
    body: JSON.stringify({
      message: 'New training created',
      input: event,
    }),
  };
};
