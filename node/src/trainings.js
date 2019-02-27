const mysql = require('mysql');

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

    const connection = dbConnection();

    connection.connect();

    try {
        // TODO move getting organizer id from input to user login
        await connection.query('INSERT INTO trainings (name, limit, date, duration, desc, organizer, room_id) VALUES (?,?,?,?,?,?,?)',
            [training.name, training.limit, training.date, training.duration, training.desc, training.organizer, training.room_id]);
    } finally {
        connection.end();
    }
}
