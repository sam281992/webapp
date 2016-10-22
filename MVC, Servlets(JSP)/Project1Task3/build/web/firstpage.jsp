<%-- 
    Document   : firstpage
    Created on : Sep 15, 2016, 7:50:31 PM
    Author     : Samyak
view of the first page in flags
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
                 <%
                    if(request.getAttribute("flagTag") != null)
                    { %>
                        <h1> The flag of <%= request.getAttribute("flagTag")%></h1>
                  <% } %>  
            
                
            
           <br>
            <form class= "flags" action="flagServlet" method="GET">
                 <%
                    if(request.getAttribute("flagURL") != null)
                    { %>
                         <img src="<%= request.getAttribute("flagURL")%>" alt=/><br><br>
                  <% } %>
                
                  
                <select name="selecter_links">  
                <option value="">Please select a country to view</option>
		
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/af.html"> Afghanistan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ax.html"> Akrotiri </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/al.html"> Albania </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ag.html"> Algeria </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/aq.html"> American Samoa </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/an.html"> Andorra </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ao.html"> Angola </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/av.html"> Anguilla </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ay.html"> Antarctica </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ac.html"> Antigua and Barbuda </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/xq.html"> Arctic Ocean </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ar.html"> Argentina </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/am.html"> Armenia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/aa.html"> Aruba </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/at.html"> Ashmore and Cartier Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/zh.html"> Atlantic Ocean </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/as.html"> Australia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/au.html"> Austria </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/aj.html"> Azerbaijan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bf.html"> Bahamas, The </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ba.html"> Bahrain </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/um.html"> Baker Island </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bg.html"> Bangladesh </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bb.html"> Barbados </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bo.html"> Belarus </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/be.html"> Belgium </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bh.html"> Belize </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bn.html"> Benin </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bd.html"> Bermuda </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bt.html"> Bhutan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bl.html"> Bolivia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bk.html"> Bosnia and Herzegovina </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bc.html"> Botswana </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bv.html"> Bouvet Island </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/br.html"> Brazil </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/io.html"> British Indian Ocean Territory </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/vi.html"> British Virgin Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bx.html"> Brunei </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bu.html"> Bulgaria </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/uv.html"> Burkina Faso </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bm.html"> Burma </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/by.html"> Burundi </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cv.html"> Cabo Verde </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cb.html"> Cambodia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cm.html"> Cameroon </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ca.html"> Canada </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cj.html"> Cayman Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ct.html"> Central African Republic </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cd.html"> Chad </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ci.html"> Chile </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ch.html"> China </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/kt.html"> Christmas Island </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ip.html"> Clipperton Island </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ck.html"> Cocos (Keeling) Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/co.html"> Colombia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cn.html"> Comoros </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cg.html"> Congo, Democratic Republic of the </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cf.html"> Congo, Republic of the </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cw.html"> Cook Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cr.html"> Coral Sea Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cs.html"> Costa Rica </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/iv.html"> Cote d'Ivoire </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/hr.html"> Croatia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cu.html"> Cuba </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cc.html"> Curacao </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cy.html"> Cyprus </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ez.html"> Czechia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/da.html"> Denmark </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/dx.html"> Dhekelia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/dj.html"> Djibouti </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/do.html"> Dominica </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/dr.html"> Dominican Republic </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ec.html"> Ecuador </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/eg.html"> Egypt </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/es.html"> El Salvador </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ek.html"> Equatorial Guinea </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/er.html"> Eritrea </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/en.html"> Estonia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/et.html"> Ethiopia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/fk.html"> Falkland Islands (Islas Malvinas) </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/fo.html"> Faroe Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/fj.html"> Fiji </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/fi.html"> Finland </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/fr.html"> France </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/fp.html"> French Polynesia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/fs.html"> French Southern and Antarctic Lands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gb.html"> Gabon </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ga.html"> Gambia, The </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gz.html"> Gaza Strip </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gg.html"> Georgia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gm.html"> Germany </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gh.html"> Ghana </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gi.html"> Gibraltar </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gr.html"> Greece </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gl.html"> Greenland </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gj.html"> Grenada </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gq.html"> Guam </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gt.html"> Guatemala </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gk.html"> Guernsey </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gv.html"> Guinea </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/pu.html"> Guinea-Bissau </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/gy.html"> Guyana </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ha.html"> Haiti </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/hm.html"> Heard Island and McDonald Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/vt.html"> Holy See (Vatican City) </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ho.html"> Honduras </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/hk.html"> Hong Kong </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/um.html"> Howland Island </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/hu.html"> Hungary </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ic.html"> Iceland </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/in.html"> India </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/xo.html"> Indian Ocean </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/id.html"> Indonesia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ir.html"> Iran </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/iz.html"> Iraq </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ei.html"> Ireland </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/im.html"> Isle of Man </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/is.html"> Israel </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/it.html"> Italy </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/jm.html"> Jamaica </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/jn.html"> Jan Mayen </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ja.html"> Japan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/um.html"> Jarvis Island </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/je.html"> Jersey </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/um.html"> Johnston Atoll </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/jo.html"> Jordan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/kz.html"> Kazakhstan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ke.html"> Kenya </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/um.html"> Kingman Reef </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/kr.html"> Kiribati </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/kn.html"> Korea, North </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ks.html"> Korea, South </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/kv.html"> Kosovo </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ku.html"> Kuwait </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/kg.html"> Kyrgyzstan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/la.html"> Laos </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/lg.html"> Latvia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/le.html"> Lebanon </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/lt.html"> Lesotho </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/li.html"> Liberia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ly.html"> Libya </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ls.html"> Liechtenstein </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/lh.html"> Lithuania </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/lu.html"> Luxembourg </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mc.html"> Macau </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mk.html"> Macedonia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ma.html"> Madagascar </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mi.html"> Malawi </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/my.html"> Malaysia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mv.html"> Maldives </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ml.html"> Mali </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mt.html"> Malta </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/rm.html"> Marshall Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mr.html"> Mauritania </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mp.html"> Mauritius </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mx.html"> Mexico </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/fm.html"> Micronesia, Federated States of </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/um.html"> Midway Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/md.html"> Moldova </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mn.html"> Monaco </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mg.html"> Mongolia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mj.html"> Montenegro </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mh.html"> Montserrat </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mo.html"> Morocco </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mz.html"> Mozambique </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/wa.html"> Namibia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/nr.html"> Nauru </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bq.html"> Navassa Island </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/np.html"> Nepal </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/nl.html"> Netherlands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/nc.html"> New Caledonia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/nz.html"> New Zealand </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/nu.html"> Nicaragua </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ng.html"> Niger </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ni.html"> Nigeria </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ne.html"> Niue </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/nf.html"> Norfolk Island </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/cq.html"> Northern Mariana Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/no.html"> Norway </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/mu.html"> Oman </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/zn.html"> Pacific Ocean </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/pk.html"> Pakistan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ps.html"> Palau </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/um.html"> Palmyra Atoll </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/pm.html"> Panama </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/pp.html"> Papua New Guinea </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/pf.html"> Paracel Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/pa.html"> Paraguay </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/pe.html"> Peru </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/rp.html"> Philippines </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/pc.html"> Pitcairn Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/pl.html"> Poland </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/po.html"> Portugal </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/rq.html"> Puerto Rico </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/qa.html"> Qatar </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ro.html"> Romania </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/rs.html"> Russia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/rw.html"> Rwanda </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tb.html"> Saint Barthelemy </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sh.html"> Saint Helena, Ascension, and Tristan da Cunha </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sc.html"> Saint Kitts and Nevis </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/st.html"> Saint Lucia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/rn.html"> Saint Martin </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sb.html"> Saint Pierre and Miquelon </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/vc.html"> Saint Vincent and the Grenadines </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ws.html"> Samoa </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sm.html"> San Marino </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tp.html"> Sao Tome and Principe </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sa.html"> Saudi Arabia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sg.html"> Senegal </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ri.html"> Serbia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/se.html"> Seychelles </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sl.html"> Sierra Leone </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sn.html"> Singapore </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sk.html"> Sint Maarten </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/lo.html"> Slovakia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/si.html"> Slovenia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/bp.html"> Solomon Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/so.html"> Somalia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sf.html"> South Africa </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/oo.html"> Southern Ocean </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sx.html"> South Georgia and South Sandwich Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/od.html"> South Sudan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sp.html"> Spain </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/pg.html"> Spratly Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ce.html"> Sri Lanka </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/su.html"> Sudan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ns.html"> Suriname </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sv.html"> Svalbard </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/wz.html"> Swaziland </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sw.html"> Sweden </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sz.html"> Switzerland </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/sy.html"> Syria </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tw.html"> Taiwan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ti.html"> Tajikistan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tz.html"> Tanzania </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/th.html"> Thailand </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tt.html"> Timor-Leste </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/to.html"> Togo </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tl.html"> Tokelau </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tn.html"> Tonga </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/td.html"> Trinidad and Tobago </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ts.html"> Tunisia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tu.html"> Turkey </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tx.html"> Turkmenistan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tk.html"> Turks and Caicos Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/tv.html"> Tuvalu </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ug.html"> Uganda </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/up.html"> Ukraine </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ae.html"> United Arab Emirates </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/uk.html"> United Kingdom </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/us.html"> United States </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/um.html"> United States Pacific Island Wildlife Refuges </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/uy.html"> Uruguay </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/uz.html"> Uzbekistan </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/nh.html"> Vanuatu </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ve.html"> Venezuela </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/vm.html"> Vietnam </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/vq.html"> Virgin Islands </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/wq.html"> Wake Island </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/wf.html"> Wallis and Futuna </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/we.html"> West Bank </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/wi.html"> Western Sahara </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ym.html"> Yemen </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/za.html"> Zambia </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/zi.html"> Zimbabwe </option>
			
				<option value="https://www.cia.gov/library/publications/the-world-factbook/geos/ee.html"> European Union </option>
            </select><button type="submit" name = "submitbutton "> Submit </button>	
                <%
                    if(request.getAttribute("textTag") != null)
                    { %>
                         <p>Text Description: <%= request.getAttribute("textTag") %></p><br><br>
                  <% } %>
                
                 
            </form>                
        
    
</html>
