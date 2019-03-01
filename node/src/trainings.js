const mysql = require('promise-mysql');

const dbConnection = () => {
    return mysql.createConnection({
        host: process.env.DB_HOST,
        user: process.env.DB_USER,
        password: process.env.DB_PSSWD,
        database: process.env.DB_NAME
    });
}

exports.create = async (training) => {
    // TODO add input validation
    const connection = await dbConnection();

    try {
        // TODO move getting organizer id from input to user login
        await connection.query('INSERT INTO trainings (name, participants_limit, date, duration, description, organizer_id, room_id) VALUES (?,?,?,?,?,?,?)',
            [training.name, training.limit, training.date, training.duration, training.desc, training.organizer, training.room_id]);
    } finally {
        connection.end();
    }
}

exports.getTraining = async (trainingId) => {
    const connection = await dbConnection();

    try {

        // TODO move getting organizer id from input to user login
        const result = await connection.query('SELECT * FROM trainings WHERE id=?', [trainingId]);

        if (result && result[0]) {
            return result[0];
        }

        return {};
    } finally {
        connection.end();
    }
}
