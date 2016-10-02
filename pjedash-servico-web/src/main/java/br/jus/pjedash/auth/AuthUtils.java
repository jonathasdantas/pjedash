package br.jus.pjedash.auth;

import java.text.ParseException;
import java.util.List;

import org.joda.time.DateTime;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import br.jus.pjedash.entidade.Usuario;

public final class AuthUtils {
	
	private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
	private static final String TOKEN_SECRET = "aliceinwonderland";
	public static final String AUTH_HEADER_KEY = "Authorization";
	
	public static String getSubject(String authHeader) throws ParseException, JOSEException {
		return decodeToken(authHeader).getSubject();
	}
	
	public static ReadOnlyJWTClaimsSet decodeToken(String authHeader) throws ParseException, JOSEException {
		SignedJWT signedJWT = SignedJWT.parse(getSerializedToken(authHeader));
		if (signedJWT.verify(new MACVerifier(TOKEN_SECRET))) {
			return signedJWT.getJWTClaimsSet();
		} else {
			throw new JOSEException("Signature verification failed");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Usuario extractUserFromToken(JWTClaimsSet claimsSet) throws ParseException, JOSEException {
		Usuario usuario = null;
		
		if (claimsSet != null) {
			usuario = new Usuario();
			
			usuario.setId(Integer.parseInt(claimsSet.getSubject()));
			usuario.setLogin(claimsSet.getStringClaim("login"));
			usuario.setNome(claimsSet.getStringClaim("nome"));
			usuario.setPapelSelecionado(claimsSet.getStringClaim("papelSelecionado"));
			usuario.setOrgaoIdSelecionado(claimsSet.getStringClaim("orgaoIdSelecionado"));
			
			usuario.setOrgaosIds( (List<String>) claimsSet.getClaim("orgaosIds"));
			usuario.setPapeis( (List<String>) claimsSet.getClaim("papeis"));
		}
		
		return usuario;
	}
	
	public static String createToken(String host, Usuario usuario) throws JOSEException {
		JWTClaimsSet claim = new JWTClaimsSet();
		
		claim.setSubject(Integer.toString(usuario.getId()));
		claim.setIssuer(host);
		claim.setIssueTime(DateTime.now().toDate());
		claim.setExpirationTime(DateTime.now().plusDays(14).toDate());

		claim.setClaim("id", usuario.getId());
		claim.setClaim("login", usuario.getLogin());
		claim.setClaim("nome", usuario.getNome());
		claim.setClaim("email", usuario.getEmail());
		claim.setClaim("orgaosIds", usuario.getOrgaosIds());
		claim.setClaim("papeis", usuario.getPapeis());
		claim.setClaim("papelSelecionado", usuario.getPapelSelecionado());
		claim.setClaim("orgaoIdSelecionado", usuario.getOrgaoIdSelecionado());
		
		JWSSigner signer = new MACSigner(TOKEN_SECRET);
		SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
		jwt.sign(signer);
		
		return jwt.serialize();
	}
	
	public static String getSerializedToken(String authHeader) {
		return authHeader.split(" ")[1];
	}
}