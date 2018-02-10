const initialState = [
];

export default function users(state = initialState, action){
    if(action.type === 'LOAD_USERS'){
        return [
            action.payload
        ];
    }
    return state;
}