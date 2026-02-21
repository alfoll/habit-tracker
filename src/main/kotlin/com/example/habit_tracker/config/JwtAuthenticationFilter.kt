package com.example.habit_tracker.config

import com.example.habit_tracker.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter (
    private val userDetailsService: UserDetailsService,
    private val tokenService: TokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")

        if ((authHeader == null || !authHeader.startsWith("Bearer "))) {
            filterChain.doFilter(request, response)
            return
        }

        val jwtToken: String = authHeader.substringAfter("Bearer ")

        val email = tokenService.getEmail(jwtToken)

        if (email != null && SecurityContextHolder.getContext().authentication == null) {
            try {
                val foundUser = userDetailsService.loadUserByUsername(email)

                if (tokenService.isValid(jwtToken, foundUser)) {
                    val authToken =
                        UsernamePasswordAuthenticationToken(foundUser, null, foundUser.authorities)
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            } catch (ex: UsernameNotFoundException) {
                SecurityContextHolder.clearContext()
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.contentType = "application/json"
                response.writer.write("""{"error": "User or list of users does not found"}""")
                return
            } catch (ex: IllegalArgumentException) {
                SecurityContextHolder.clearContext()
                response.status = HttpServletResponse.SC_BAD_REQUEST
                response.contentType = "application/json"
                response.writer.write("""{"error": "Invalid token format"}""")
                return
            } catch (ex: Exception) {
                SecurityContextHolder.clearContext()
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error")
                return
            }

            filterChain.doFilter(request, response)
        }
    }
}