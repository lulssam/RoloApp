package dam.a50799.prj_roloapp.data.auth

data class SignInResults(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)